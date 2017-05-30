package com.person.cloud.storm.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {
	private static ApplicationContext applicationContext;
	public static synchronized ApplicationContext SpringContextInit() {
		if (applicationContext == null) {
			applicationContext = new ClassPathXmlApplicationContext("clound-jdbc-applicationContext.xml");
		}
		return SpringContext.applicationContext;
	}
}