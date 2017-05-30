package com.person.cloud.parse;

import org.junit.Test;

import com.person.cloud.common.BaseJunit4Test;
import com.person.cloud.parse.impl.CsvParser;

public class CSVParserTest extends BaseJunit4Test {
	// private final int SIZE = 5;
	// private String filePath = "D:\\temp\\1000W-1200W.csv";
	//private String filePath = "D:\\temp\\800W-1000W.csv";
	private String filePath = "D:\\temp\\1-200W.csv";
	private final int SIZE = 32;

	@Test
	public void testNewCsvParse() {
		long start = System.currentTimeMillis();
		CsvParser newAp = new CsvParser();
		newAp.parse(SIZE, filePath);
		long end = System.currentTimeMillis();
		System.out.println("--------------------spend time[" + ((end - start) / 1000) + "]-----------------------");
		// NewCustomCsvParser newAp1 = new NewCustomCsvParser(filePath);
		// newAp1.parse();
	}
}
