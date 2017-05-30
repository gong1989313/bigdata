package com.person.cloud.task;

import java.io.File;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.person.cloud.common.queue.ServiceQueue;

public class FileWatchTask implements Runnable {
	private static transient Log logger = LogFactory.getLog(FileWatchTask.class);
	private BlockingQueue<String> csvTaskQueue = ServiceQueue.getInstance().getCsvTaskQueue();
	private String filePath;

	public FileWatchTask(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		logger.info("FileWatchTask:" + threadName + " have started.");
		File csvFileDir = new File(filePath);
		if (csvFileDir.exists()) {
			if (csvFileDir.isDirectory()) {
				File[] csvFiles = csvFileDir.listFiles();
				if (csvFiles == null) {
					return;
				}
				for (File csvFile : csvFiles) {
					try {
						csvTaskQueue.put(csvFile.getAbsolutePath());
						logger.info("put csvFile:" + csvFile + " to csvTaskQueue success...");
					} catch (InterruptedException e) {
						logger.error("put csvFile:" + csvFile + " to csvTaskQueue failed...", e);
						e.printStackTrace();
					}
				}
			} else {
				logger.error("File Path:" + filePath + " is not Directory...");
			}
		} else {
			logger.error("File Path:" + filePath + " is not exists...");
		}
	}
}
