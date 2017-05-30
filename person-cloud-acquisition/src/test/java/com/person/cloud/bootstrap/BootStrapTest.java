package com.person.cloud.bootstrap;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.person.cloud.common.BaseJunit4Test;
import com.person.cloud.task.FileWatchTask;
import com.person.cloud.task.KafkaMsgProducerTask;
import com.person.cloud.task.QueueMsgProducerTask;
import com.person.cloud.utils.ConfigInfo;
import com.person.cloud.utils.ThreadPoolUtils;

public class BootStrapTest extends BaseJunit4Test {
	private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);

	@Test
	public void testBootStrap() {
		scheduledThreadPool.scheduleWithFixedDelay(new FileWatchTask(ConfigInfo.fwPath), 0, ConfigInfo.waitSeconds,
				TimeUnit.SECONDS);
		for (int i = 0; i < ConfigInfo.kafkaProducerNum; i++) {
			ThreadPoolUtils.submitTask(new KafkaMsgProducerTask("test", 1));
		}

		for (int i = 0; i < ConfigInfo.queueProducerNum; i++) {
			ThreadPoolUtils.submitTask(new QueueMsgProducerTask());
		}
		
		try {
			TimeUnit.SECONDS.sleep(60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
