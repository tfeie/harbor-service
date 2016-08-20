package com.the.harbor.test;

import com.taobao.api.internal.tmc.Message;
import com.taobao.api.internal.tmc.MessageHandler;
import com.taobao.api.internal.tmc.MessageStatus;
import com.taobao.api.internal.tmc.TmcClient;
import com.taobao.api.internal.toplink.LinkException;

public class Test {

	public static void main(String[] args) throws LinkException {
		TmcClient client = new TmcClient("23433832", "2a7862f728ec7fafc0015709e780f29e", "default");
		client.connect("ws://mc.api.taobao.com/");
		client.setMessageHandler(new MessageHandler() {
			public void onMessage(Message message, MessageStatus status) {
				try {
					System.out.println(message.getContent());
					System.out.println(message.getTopic());
				} catch (Exception e) {
					e.printStackTrace();
					status.fail();
				}
			}
		});

		synchronized (Test.class) {
			while (true) {
				try {
					System.out.println("hhhh");
					Test.class.wait();

				} catch (Exception e) {
				}
			}
		}
		// client.connect();

	}

}
