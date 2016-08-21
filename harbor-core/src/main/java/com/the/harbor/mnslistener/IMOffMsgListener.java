package com.the.harbor.mnslistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.taobao.api.internal.tmc.Message;
import com.taobao.api.internal.tmc.MessageHandler;
import com.taobao.api.internal.tmc.MessageStatus;
import com.taobao.api.internal.tmc.TmcClient;
import com.the.harbor.api.user.param.UserViewInfo;
import com.the.harbor.base.enumeration.hynotify.AccepterType;
import com.the.harbor.base.enumeration.hynotify.NotifyType;
import com.the.harbor.base.enumeration.hynotify.SenderType;
import com.the.harbor.commons.components.aliyuncs.im.IMSettings;
import com.the.harbor.commons.redisdata.def.DoNotify;
import com.the.harbor.commons.util.StringUtil;
import com.the.harbor.commons.util.UUIDUtil;
import com.the.harbor.service.interfaces.IUserManagerSV;
import com.the.harbor.util.NotifyMQSend;

/**
 * IM离线消息通知监听
 * 
 * @author zhangchao
 *
 */
public class IMOffMsgListener implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(IMOffMsgListener.class);

	@Autowired
	IUserManagerSV userManagerSV;

	@Override
	public void afterPropertiesSet() throws Exception {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					processOffMsg(userManagerSV);
				} catch (Exception ex) {
					LOG.error("离线消息通知失败", ex);
				}
			}
		});
		thread.start();
	}

	private void processOffMsg(final IUserManagerSV userManagerSV) throws Exception {
		TmcClient client = new TmcClient(IMSettings.getAppKey(), IMSettings.getAppSecret(), "default");
		client.connect("ws://mc.api.taobao.com/");
		client.setMessageHandler(new MessageHandler() {
			public void onMessage(Message message, MessageStatus status) {
				try {
					String msg = message.getContent();
					if (!StringUtil.isBlank(msg)) {
						JSONObject data = JSONObject.parseObject(msg);
						String fromId = data.getString("from_id");
						String toId = data.getString("to_id");
						UserViewInfo sendUser = userManagerSV.getUserViewInfoByUserId(fromId);

						DoNotify body = new DoNotify();
						body.setNotifyId(UUIDUtil.genId32());
						body.setHandleType(DoNotify.HandleType.PUBLISH.name());
						body.setNotifyType(NotifyType.SYSTEM_NOTIFY.getValue());
						body.setSenderType(SenderType.USER.getValue());
						body.setSenderId(fromId);
						body.setAccepterType(AccepterType.USER.getValue());
						body.setAccepterId(toId);
						body.setTitle("您有新的离线消息");
						body.setContent("[" + sendUser.getEnName() + "]给您发了新的离线消息，请查看~");
						body.setLink("../user/im.html?touchId=" + fromId+"&notifyId="+body.getNotifyId());
						NotifyMQSend.sendNotifyMQ(body);
					}
				} catch (Exception e) {
					status.fail();
				}
			}
		});
	}

}
