package com.person.cloud.storm.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		if (applicationContext == null) {
			applicationContext = arg0;
		}
	}

	public static <T> T getBean(String name, Class<T> clazz) {
		if(applicationContext == null){
			applicationContext = SpringContext.SpringContextInit();
		}
		System.out.println("application is :"+ applicationContext);
		return applicationContext.getBean(name, clazz);
	}
}
