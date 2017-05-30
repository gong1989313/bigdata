package com.person.cloud.kafka;

import org.junit.Test;

import com.person.cloud.sender.impl.KafkaMessageProducer;

public class KafkaProducerTest {

	@Test
	public void test() {
		KafkaMessageProducer kafkaProducer = KafkaMessageProducer  
                .getInstance();
		kafkaProducer.init("test", 2);
		for(int i=0; i<100; i++){
			kafkaProducer.sendMessage("gxq-test:"+i);
		}
	}
}
