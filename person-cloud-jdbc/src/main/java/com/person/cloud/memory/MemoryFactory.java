package com.person.cloud.memory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemoryFactory {

	private MemoryFactory() {}

	private static class SingletonHolder {
		public static final Memory MEMORY = new Memory(getDataSource());
		//public static final Memory MEMORY = new Memory(new SimpleDataSource());
	}

	public static Memory getInstance() {
		return SingletonHolder.MEMORY;
	}
	
	public static final DataSource getDataSource() {
		try {
			Context context = new InitialContext();
			return (DataSource) context.lookup("java:comp/env/jdbc/test");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
}
