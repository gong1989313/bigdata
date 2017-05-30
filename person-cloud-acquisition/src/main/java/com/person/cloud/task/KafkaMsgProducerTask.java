package com.person.cloud.task;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.person.cloud.common.model.OriginPerson;
import com.person.cloud.common.queue.ServiceQueue;
import com.person.cloud.sender.impl.KafkaMessageProducer;

public class KafkaMsgProducerTask implements Runnable {
	private static transient Log logger = LogFactory.getLog(KafkaMsgProducerTask.class);
	private Queue<List<String>> csvTxtDataQueue = ServiceQueue.getInstance().getCsvTxtDataQueue();
	KafkaMessageProducer kafkaProducer;

	public KafkaMsgProducerTask(String topic, int retry) {
		kafkaProducer = KafkaMessageProducer.getInstance();
		kafkaProducer.init(topic, retry);
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		logger.info("KafkaMsgProducerTask:" + threadName + " have started.");
		List<String> csvTxtData;
		try {
			while (!Thread.currentThread().isInterrupted()) {
				if (!csvTxtDataQueue.isEmpty()) {
					csvTxtData = csvTxtDataQueue.poll();
					if (csvTxtData != null) {
						String jsonMsg = this.convertJson(csvTxtData);
						kafkaProducer.sendMessage(jsonMsg);
					}
				} else {
					TimeUnit.MILLISECONDS.sleep(100);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in task:" + Thread.currentThread().getName() + " thread.", e);
		}
	}

	private String convertJson(List<String> csvTxtData) {
		OriginPerson origPerson = new OriginPerson();
		origPerson.setName(csvTxtData.get(0));
		origPerson.setCardno(csvTxtData.get(1));
		origPerson.setDescriot(csvTxtData.get(2));
		origPerson.setCtftp(csvTxtData.get(3));
		origPerson.setCtfid(csvTxtData.get(4));
		origPerson.setGender(csvTxtData.get(5));
		origPerson.setBirthday(csvTxtData.get(6));
		origPerson.setAddress(csvTxtData.get(7));
		origPerson.setZip(csvTxtData.get(8));
		origPerson.setDirty(csvTxtData.get(9));
		origPerson.setDistrct1(csvTxtData.get(10));
		origPerson.setDistrct2(csvTxtData.get(11));
		origPerson.setDistrct3(csvTxtData.get(12));
		origPerson.setDistrct4(csvTxtData.get(13));
		origPerson.setDistrct5(csvTxtData.get(14));
		origPerson.setDistrct6(csvTxtData.get(15));
		origPerson.setFirstnm(csvTxtData.get(16));
		origPerson.setLastnm(csvTxtData.get(17));
		origPerson.setDuty(csvTxtData.get(18));
		origPerson.setMobile(csvTxtData.get(19));
		origPerson.setTel(csvTxtData.get(20));
		origPerson.setFax(csvTxtData.get(21));
		origPerson.setEmail(csvTxtData.get(22));
		origPerson.setNation(csvTxtData.get(23));
		origPerson.setTaste(csvTxtData.get(24));
		origPerson.setEducation(csvTxtData.get(25));
		origPerson.setCompany(csvTxtData.get(26));
		origPerson.setCtel(csvTxtData.get(27));
		origPerson.setCaddress(csvTxtData.get(28));
		origPerson.setCzip(csvTxtData.get(29));
		origPerson.setFamily(csvTxtData.get(30));
		origPerson.setVersion(csvTxtData.get(31));
		origPerson.setId(csvTxtData.get(32));
		return JSON.toJSONString(origPerson);
	}
}
