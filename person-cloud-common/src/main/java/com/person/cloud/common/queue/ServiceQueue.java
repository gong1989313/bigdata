package com.person.cloud.common.queue;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServiceQueue {
	private volatile static BlockingQueue<String> csvTaskQueue = new LinkedBlockingQueue<String>(10);
	private volatile static Queue<List<String>> csvTxtDataQueue = new ConcurrentLinkedQueue<List<String>>();

	private ServiceQueue() {
	}

	private static ServiceQueue instance = new ServiceQueue();

	public static ServiceQueue getInstance() {
		return null == instance ? new ServiceQueue() : instance;
	}

	public BlockingQueue<String> getCsvTaskQueue() {
		return csvTaskQueue;
	}

	public Queue<List<String>> getCsvTxtDataQueue() {
		return csvTxtDataQueue;
	}
}
