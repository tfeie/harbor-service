package com.the.harbor.mnslistener;

import java.util.Set;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import com.the.harbor.api.be.param.Be;
import com.the.harbor.api.be.param.BeComment;
import com.the.harbor.api.be.param.DoBeIndexRealtimeStat;
import com.the.harbor.commons.components.aliyuncs.mns.MNSSettings;
import com.the.harbor.commons.components.aliyuncs.mns.MessageReceiver;
import com.the.harbor.commons.components.elasticsearch.ElasticSearchFactory;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.indices.def.HarborIndex;
import com.the.harbor.commons.indices.def.HarborIndexType;
import com.the.harbor.commons.indices.mq.MNSRecordHandle;
import com.the.harbor.commons.redisdata.util.HyBeUtil;
import com.the.harbor.commons.util.StringUtil;

public class BeIndexRealtimeCountListener implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(BeIndexRealtimeCountListener.class);

	protected static MNSClient sMNSClient;

	@Override
	public void afterPropertiesSet() throws Exception {
		// 为了防止阻塞dubbo服务启动，这里采用一个异步线程唤醒消息接收工作线程
		Thread thread = new Thread(new Runnable() {
			public void run() {
				runThread();
			}
		});
		thread.start();
	}

	public static void runThread() {
		CloudAccount account = new CloudAccount(MNSSettings.getMNSAccessKeyId(), MNSSettings.getMNSAccessKeySecret(),
				MNSSettings.getMNSAccountEndpoint());
		sMNSClient = account.getMNSClient();

		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(1);
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(2);
			}
		});
		Thread thread3 = new Thread(new Runnable() {
			public void run() {
				WorkerFunc(3);
			}
		});

		thread1.start();
		thread2.start();
		thread3.start();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// keep looping util the end of world
		try {
			thread1.join();
			thread2.join();
			thread3.join();
		} catch (InterruptedException e) {
			LOG.error("BE实时统计数据索引更新存储工作线程启动失败", e);
		}
	}

	public static void WorkerFunc(int workerId) {
		String queueName = GlobalSettings.getBeIndexRealtimeCountQueueName();
		MessageReceiver receiver = new MessageReceiver(workerId, sMNSClient, queueName);
		while (true) {
			boolean success = true;
			String error = null;
			String mqId = null;
			String mqType = null;
			Message message = receiver.receiveMessage();
			LOG.info("Thread" + workerId + " GOT ONE MESSAGE! " + message.getMessageId());
			try {
				if (!StringUtil.isBlank(message.getMessageBody())) {
					DoBeIndexRealtimeStat stat = JSONObject.parseObject(message.getMessageBody(),
							DoBeIndexRealtimeStat.class);
					mqId = stat.getMqId();
					mqType = stat.getMqType();
					if (!StringUtil.isBlank(stat.getBeId())) {
						Client client = ElasticSearchFactory.getClient();
						SearchResponse response = client.prepareSearch(HarborIndex.HY_BE_DB.getValue())
								.setTypes(HarborIndexType.HY_BE.getValue())
								.setQuery(QueryBuilders.termQuery("_id", stat.getBeId())).execute().actionGet();
						if (response.getHits().totalHits() == 0) {
							return;
						}
						// 从ES获取BE数据，后续作废
						Be be = JSON.parseObject(response.getHits().getHits()[0].getSourceAsString(), Be.class);
						// 从阿里云REDIS获取数据
						String data = HyBeUtil.getBe(stat.getBeId());
						Be newBe = JSONObject.parseObject(data, Be.class);
						if (DoBeIndexRealtimeStat.StatType.COMMENT.name().equals(stat.getStatType())) {
							Set<String> set = HyBeUtil.getBeCommentIds(be.getBeId(), 0, -1);
							long count = 0;
							// 只计算有效的评论记录
							for (String commentId : set) {
								String commentData = HyBeUtil.getBeComment(commentId);
								if (!StringUtil.isBlank(commentData)) {
									BeComment b = JSONObject.parseObject(commentData, BeComment.class);
									if (com.the.harbor.base.enumeration.hybecomments.Status.NORMAL.getValue()
											.equals(b.getStatus())) {
										count++;
									}
								}
							}
							be.setCommentCount(count);

							if (newBe != null) {
								newBe.setCommentCount(count);
							}
						} else if (DoBeIndexRealtimeStat.StatType.DIANZAN.name().equals(stat.getStatType())) {
							long count = HyBeUtil.getBeDianzanCount(be.getBeId());
							be.setDianzanCount(count);

							if (newBe != null) {
								newBe.setDianzanCount(count);
							}
						} else if (DoBeIndexRealtimeStat.StatType.REWARD.name().equals(stat.getStatType())) {
							long count = HyBeUtil.getBeRewardHBCount(be.getBeId());
							be.setGiveHaibeiCount(count);

							if (newBe != null) {
								newBe.setGiveHaibeiCount(count);
							}
						}
						client.prepareIndex(HarborIndex.HY_BE_DB.getValue().toLowerCase(),
								HarborIndexType.HY_BE.getValue().toLowerCase(), be.getBeId()).setRefresh(true)
								.setSource(JSON.toJSONString(be)).execute().actionGet();

						// 写入REDIS缓存
						if (newBe != null) {
							HyBeUtil.recordBe(newBe.getBeId(), JSON.toJSONString(newBe));
						}
					}

				}
			} catch (Exception ex) {
				LOG.error("BE实时统计数据索引更新存储MNS消息消费失败", ex);
				success = false;
				error = ex.getMessage();
			}
			try {
				if (success) {
					if (!StringUtil.isBlank(message.getReceiptHandle())) {
						sMNSClient.getQueueRef(queueName).deleteMessage(message.getReceiptHandle());
					}
				}
				if (!StringUtil.isBlank(mqId) && !StringUtil.isBlank(mqType)) {
					MNSRecordHandle.processMNSRecord(mqId, mqType, success, error);
				}

			} catch (Exception ex) {
				LOG.error("消息删除失败", ex);
			}
		}
	}

}
