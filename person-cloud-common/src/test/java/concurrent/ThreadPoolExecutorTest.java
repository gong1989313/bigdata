package concurrent;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.junit.Test;

public class ThreadPoolExecutorTest {

	@Test
	public void test() {
		Executors.callable(new Runnable() {
			@Override
			public void run() {
			}
		}, "");
	}
	
	@Test
	public void testScheduled() throws InterruptedException, ExecutionException {
		ScheduledThreadPoolExecutor executor = null;
		DelayQueue dq = null;
		dq.take();
		FutureTask f = null;
		f.get();
	}
}
