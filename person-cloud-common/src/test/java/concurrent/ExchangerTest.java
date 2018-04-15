package concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {
	private static final Exchanger<String> exgr = new Exchanger<String>();
	
	private static ExecutorService threadPool = Executors.newFixedThreadPool(2);
	
	public static void main(String[] args) {
		threadPool.execute(new Runnable() {
			String A = "bank A";
			@Override
			public void run() {
				try {
					exgr.exchange(A);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		threadPool.execute(new Runnable() {
			String B = "bank B";
			@Override
			public void run() {
				try {
					String a = exgr.exchange(B);
					System.out.println(a   +" : "+ a.equals(B));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		threadPool.shutdown();
	}
}
