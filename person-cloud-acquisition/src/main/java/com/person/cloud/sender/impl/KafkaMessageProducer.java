package com.person.cloud.sender.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.person.cloud.sender.MessageProducer;

public final class KafkaMessageProducer implements MessageProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageProducer.class);

	private static KafkaProducer<String, String> kafkaProducer;
	private Random random = new Random();
	private String topic;
	private int retry;

	private KafkaMessageProducer() {

	}

	private static class LazyHandler {
		private static final KafkaMessageProducer instance = new KafkaMessageProducer();
	}

	/**
	 * 单例模式,kafkaProducer
	 * 
	 * @return
	 */
	public static final KafkaMessageProducer getInstance() {
		return LazyHandler.instance;
	}

	/**
	 * kafka
	 * 
	 * @return KafkaProducer
	 */
	public void init(String topic, int retry) {
		this.topic = topic;
		this.retry = retry;
		if (null == kafkaProducer) {
			Properties props = new Properties();
			InputStream inStream = null;
			try {
				inStream = this.getClass().getClassLoader().getResourceAsStream("conf/kafka.properties");
				props.load(inStream);
				kafkaProducer = new KafkaProducer<String, String>(props);
			} catch (IOException e) {
				LOGGER.error("kafkaProducer初始化失败:" + e.getMessage(), e);
			} finally {
				if (null != inStream) {
					try {
						inStream.close();
					} catch (IOException e) {
						LOGGER.error("kafkaProducer初始化失败:" + e.getMessage(), e);
					}
				}
			}
		}
	}
	
	/**
	 * @param topic
	 * @param partitionNum
	 * @param retry
	 * @param message
	 */
	@Override
	public void sendMessage(final String message) {
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, random.nextInt(1), "",
				message);
		kafkaProducer.send(record, new Callback() {
			public void onCompletion(RecordMetadata recordMetadata, Exception exception) {
				if (null != exception) {
					LOGGER.error("kafka send message failed:" + exception.getMessage(), exception);
					retryKakfaMessage(message);
				}
			}
		});
	}

	/**
	 * @param retryMessage
	 */
	private void retryKakfaMessage(final String retryMessage) {
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, random.nextInt(3), "",
				retryMessage);
		for (int i = 1; i <= retry; i++) {
			try {
				kafkaProducer.send(record);
				return;
			} catch (Exception e) {
				LOGGER.error("kafka send msg failed:" + e.getMessage(), e);
				retryKakfaMessage(retryMessage);
			}
		}
	}

	/**
	 * kafka close
	 */
	public void close() {
		if (null != kafkaProducer) {
			kafkaProducer.close();
		}
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}
}