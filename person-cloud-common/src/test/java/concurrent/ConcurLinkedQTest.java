package concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedTransferQueue;

import org.junit.Test;

public class ConcurLinkedQTest {

	@Test
	public void test() {
		ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<String>();
		clq.offer("a");
		clq.offer("b");
		clq.offer("c");
		clq.offer("d");
		clq.offer("e");
		FutureTask ft ;
	}

	@Test
	public void testDelayQueue() {
		DelayQueue clq = new DelayQueue();
		clq.put(null);
	}
	
	@Test
	public void testLinkedTransferQueue() {
		LinkedTransferQueue ltq = null;
	}
}
