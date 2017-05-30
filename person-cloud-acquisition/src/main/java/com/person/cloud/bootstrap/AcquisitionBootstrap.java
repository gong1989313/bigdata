package com.person.cloud.bootstrap;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.person.cloud.task.FileWatchTask;
import com.person.cloud.task.KafkaMsgProducerTask;
import com.person.cloud.task.QueueMsgProducerTask;
import com.person.cloud.utils.ConfigInfo;
import com.person.cloud.utils.ThreadPoolUtils;

public class AcquisitionBootstrap {
	private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);

	public static void main(String[] args) {
		@SuppressWarnings({ "unused", "resource" })
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("conf/clound-acquisition-applicationContext.xml");
		scheduledThreadPool.scheduleWithFixedDelay(new FileWatchTask(ConfigInfo.fwPath), 0, ConfigInfo.waitSeconds,
				TimeUnit.SECONDS);
		for (int i = 0; i < ConfigInfo.kafkaProducerNum; i++) {
			ThreadPoolUtils.submitTask(new KafkaMsgProducerTask("test", 1));
		}
		
		for (int i = 0; i < ConfigInfo.queueProducerNum; i++) {
			ThreadPoolUtils.submitTask(new QueueMsgProducerTask());
		}
	}
}
