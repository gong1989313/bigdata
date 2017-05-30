package com.person.clound.kafka.message;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.junit.Test;

import com.person.clound.kafka.domains.KafkaConstants;

public class MessageSenderPoolTest {

	@Test
	public void testDefault() {
		long begin = System.currentTimeMillis();
		try {
			// specify the broker ip directly
			MessageSenderPool pool = new MessageSenderPool(Runtime.getRuntime().availableProcessors() * 2 + 1, "test",
					"192.168.2.31:9092,192.168.2.32:9092,192.168.2.33:9092");

			long mid = System.currentTimeMillis();
			System.out.println("Init sender cost: " + (mid - begin));
			MessageEncoder<String> stringEncoder = new MessageEncoder<String>() {

				@Override
				public byte[] encode(String msg) {
					byte[] ret = null;
					try {
						ret = msg.getBytes("UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					return ret;
				}

			};
			for (int i = 0; i < 20; i++) {
				MessageSender sender = pool.getSender(2000);
				sender.send(String.valueOf(i), stringEncoder);
				sender.close();
			}
			long end = System.currentTimeMillis();
			System.out.println("Send cost: " + (end - mid));
			// close the pool to exit
			pool.close();
			long finished = System.currentTimeMillis();
			System.out.println("Close cost: " + (finished - end));
			System.err.println("Send message OK: Init[" + (mid - begin) + "], Send[" + (end - mid) + "], Close["
					+ (finished - end) + "]");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	@Test
	public void testAsync() {
		long begin = System.currentTimeMillis();
		try {
			Properties p = new Properties();
			p.setProperty(KafkaConstants.PRODUCER_TYPE, "async");

			// specify the broker ip directly
			MessageSenderPool pool = new MessageSenderPool(Runtime.getRuntime().availableProcessors() * 2 + 1, "test",
					"192.168.2.31:9092,192.168.2.32:9092,192.168.2.33:9092");

			long mid = System.currentTimeMillis();
			System.out.println("Init sender cost: " + (mid - begin));
			MessageEncoder<String> stringEncoder = new MessageEncoder<String>() {

				@Override
				public byte[] encode(String msg) {
					byte[] ret = null;
					try {
						ret = msg.getBytes("UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					return ret;
				}

			};
			for (int i = 0; i < 20; i++) {
				MessageSender sender = pool.getSender(2000);
				sender.send(String.valueOf(i), stringEncoder);
				sender.close();
			}
			long end = System.currentTimeMillis();
			System.out.println("Send cost: " + (end - mid));
			// close the pool to exit
			pool.close();
			long finished = System.currentTimeMillis();
			System.out.println("Close cost: " + (finished - end));
			System.err.println("Send message OK: Init[" + (mid - begin) + "], Send[" + (end - mid) + "], Close["
					+ (finished - end) + "]");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
