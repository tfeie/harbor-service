package com.the.harbor.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class BeGoMQListenerStart {

	private static final Logger LOG = LoggerFactory.getLogger(BeGoMQListenerStart.class);

	private static final String PATH = "classpath:context/bego-mq-listener-context.xml";

	private BeGoMQListenerStart() {
	}

	public static void main(String[] args) {
		LOG.error("开始启动MQ监听......");
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { PATH });
		context.start();
		LOG.error("监听启动成功.....");
		synchronized (BeGoMQListenerStart.class) {
			while (true) {
				try {
					BeGoMQListenerStart.class.wait();
				} catch (Exception e) {
					LOG.error("MQ监听启动失败具体信息为：" + e.getMessage(), e);
				}
			}
		}
	}
}
