package concurrent;

import static org.junit.Assert.*;

import java.util.concurrent.Executors;

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

}
