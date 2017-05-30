package com.person.cloud.jdbc.dao.impl;

import org.crazycake.jdbcTemplateTool.JdbcTemplateTool;
import org.crazycake.jdbcTemplateTool.exception.NoColumnAnnotationFoundException;
import org.crazycake.jdbcTemplateTool.exception.NoIdAnnotationFoundException;

import com.person.cloud.jdbc.dao.PersonDAO;
import com.person.cloud.jdbc.pojo.Person;

public class PersonDAOImpl implements PersonDAO {
	private JdbcTemplateTool jdbcTemplateTool;

	@Override
	public void persistence(Person person) {
		try {
			jdbcTemplateTool.save(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Person findById(Integer id) {
		try {
			return jdbcTemplateTool.get(Person.class, id);
		} catch (NoIdAnnotationFoundException e) {
			e.printStackTrace();
		} catch (NoColumnAnnotationFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JdbcTemplateTool getJdbcTemplateTool() {
		return jdbcTemplateTool;
	}

	public void setJdbcTemplateTool(JdbcTemplateTool jdbcTemplateTool) {
		this.jdbcTemplateTool = jdbcTemplateTool;
	}

}
