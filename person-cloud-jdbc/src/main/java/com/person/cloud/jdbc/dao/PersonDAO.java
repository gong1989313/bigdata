package com.person.cloud.jdbc.dao;

import com.person.cloud.jdbc.pojo.Person;

public interface PersonDAO {
	void persistence(Person person);
	Person findById(Integer id);
}
