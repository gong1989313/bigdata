package com.person.cloud.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class ThreadPoolUtils {
	private static ExecutorService EXEC_SERVICE = Executors.newFixedThreadPool(6, new ThreadFactory() {

		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			t.setDaemon(true);
			return t;
		}
	});

	public static <V> Future<V> submitTask(Callable<V> task) {
		return EXEC_SERVICE.submit(task);
	}

	public static <V> Future<?> submitTask(Runnable task) {
		return EXEC_SERVICE.submit(task);
	}
}
