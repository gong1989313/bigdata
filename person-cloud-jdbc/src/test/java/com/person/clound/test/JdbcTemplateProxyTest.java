package com.person.clound.test;

import org.crazycake.jdbcTemplateTool.JdbcTemplateProxy;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:clound-jdbc-applicationContext.xml" })
public class JdbcTemplateProxyTest extends AbstractJUnit4SpringContextTests {
	@Test
	public void testSetJdbcTemplate() {
		JdbcTemplate jdbcTemplate = super.applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
		JdbcTemplateProxy proxy = new JdbcTemplateProxy();
		proxy.setJdbcTemplate(jdbcTemplate);
	}
}
