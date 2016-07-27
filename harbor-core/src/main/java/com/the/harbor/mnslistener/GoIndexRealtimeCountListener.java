package com.the.harbor.mnslistener;

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
import com.the.harbor.api.go.param.DoGoIndexRealtimeStat;
import com.the.harbor.api.go.param.Go;
import com.the.harbor.commons.components.aliyuncs.mns.MNSSettings;
import com.the.harbor.commons.components.aliyuncs.mns.MessageReceiver;
import com.the.harbor.commons.components.elasticsearch.ElasticSearchFactory;
import com.the.harbor.commons.components.globalconfig.GlobalSettings;
import com.the.harbor.commons.indices.def.HarborIndex;
import com.the.harbor.commons.indices.def.HarborIndexType;
import com.the.harbor.commons.indices.mq.MNSRecordHandle;
import com.the.harbor.commons.redisdata.util.HyGoUtil;
import com.the.harbor.commons.util.StringUtil;

public class GoIndexRealtimeCountListener implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(GoIndexRealtimeCountListener.class);

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
			LOG.error("GO实时统计数据索引更新存储工作线程启动失败", e);
		}
	}

	public static void WorkerFunc(int workerId) {
		String queueName = GlobalSettings.getGoIndexRealtimeCountQueueName();
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
					DoGoIndexRealtimeStat stat = JSONObject.parseObject(message.getMessageBody(),
							DoGoIndexRealtimeStat.class);
					mqId = stat.getMqId();
					mqType = stat.getMqType();
					if (!StringUtil.isBlank(stat.getGoId())) {
						Client client = ElasticSearchFactory.getClient();
						SearchResponse response = client.prepareSearch(HarborIndex.HY_GO_DB.getValue())
								.setTypes(HarborIndexType.HY_GO.getValue())
								.setQuery(QueryBuilders.termQuery("_id", stat.getGoId())).execute().actionGet();
						if (response.getHits().totalHits() == 0) {
							return;
						}
						Go go = JSON.parseObject(response.getHits().getHits()[0].getSourceAsString(), Go.class);
						if (DoGoIndexRealtimeStat.StatType.FAVOR.name().equals(stat.getStatType())) {
							long count = HyGoUtil.getGoFavoredUserCount(go.getGoId());
							go.setFavorCount(count);
						} else if (DoGoIndexRealtimeStat.StatType.GROUPJOIN.name().equals(stat.getStatType())) {
							long joinCount = HyGoUtil.getGroupConfirmedJoinUsersCount(go.getGoId());
							go.setJoinCount(joinCount);
						} else if (DoGoIndexRealtimeStat.StatType.VIEW.name().equals(stat.getStatType())) {
							long count = HyGoUtil.getGoViewCount(stat.getGoId());
							go.setViewCount(count);
						}
						client.prepareIndex(HarborIndex.HY_GO_DB.getValue().toLowerCase(),
								HarborIndexType.HY_GO.getValue().toLowerCase(), go.getGoId()).setRefresh(true)
								.setSource(JSON.toJSONString(go)).execute().actionGet();
					}

				}
			} catch (Exception ex) {
				LOG.error("GO实时统计数据索引更新存储MNS消息消费失败", ex);
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
