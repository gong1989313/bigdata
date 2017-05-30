package com.person.cloud.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.person.cloud.common.queue.ServiceQueue;
import com.person.cloud.parse.impl.CsvParser;
import com.person.cloud.utils.ConfigInfo;

public class QueueMsgProducerTask implements Runnable {
	private static transient Log logger = LogFactory.getLog(QueueMsgProducerTask.class);
	private BlockingQueue<String> csvTaskQueue = ServiceQueue.getInstance().getCsvTaskQueue();
	private final CsvParser csvParser = new CsvParser();

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		logger.info("QueueMsgProducerTask:" + threadName + " have started.");
		String csvFile;
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!csvTaskQueue.isEmpty()) {
					long start = System.currentTimeMillis();
					csvFile = csvTaskQueue.take();
					if (csvFile != null) {
						csvParser.parse(ConfigInfo.csvFieldNum, csvFile);
						long end = System.currentTimeMillis();
						logger.info("---------- "+threadName+"[CsvParser spend time:" + (end - start) + "] ----------");
					}
				} else {
					TimeUnit.MILLISECONDS.sleep(100);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in task:" + Thread.currentThread().getName() + " thread.", e);
		}
	}
}
