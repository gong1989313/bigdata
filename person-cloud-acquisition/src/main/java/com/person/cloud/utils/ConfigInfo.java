package com.person.cloud.utils;

import org.apache.commons.lang3.StringUtils;

public class ConfigInfo {
	public static String fwPath;
	public static int queueProducerNum;
	public static int kafkaProducerNum;
	public static int csvFieldNum;
	public static int waitSeconds;
	public static char delimiter;

	public static void setFwPath(String fwPath) {
		ConfigInfo.fwPath = fwPath;
	}

	public static void setQueueProducerNum(String queueProducerNum) {
		if (StringUtils.isNumeric(queueProducerNum)) {
			ConfigInfo.queueProducerNum = Integer.parseInt(queueProducerNum);
		}
	}
	
	public static void setKafkaProducerNum(String kafkaProducerNum) {
		if (StringUtils.isNumeric(kafkaProducerNum)) {
			ConfigInfo.kafkaProducerNum = Integer.parseInt(kafkaProducerNum);
		}
	}

	public static void setCsvFieldNum(String csvFieldNum) {
		if (StringUtils.isNumeric(csvFieldNum)) {
			ConfigInfo.csvFieldNum = Integer.parseInt(csvFieldNum);
		}
	}

	public static void setDelimiter(char delimiter) {
		ConfigInfo.delimiter = delimiter;
	}

	public static void setWaitSeconds(String waitSeconds) {
		if (StringUtils.isNumeric(waitSeconds)) {
			ConfigInfo.waitSeconds = Integer.parseInt(waitSeconds);
		}
	}
}
