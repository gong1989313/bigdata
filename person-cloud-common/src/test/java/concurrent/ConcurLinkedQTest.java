package concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.FutureTask;

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

}
