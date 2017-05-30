package com.person.cloud.parse;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class IOTest {

	private String filePath = "D:\\develop\\workspace\\git\\person-cloud\\person-cloud-acquisition\\src\\test\\resources\\data\\50.csv";

	@Test
	public void testIO() {
		int chInt = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(
					new InputStreamReader(new BufferedInputStream(new FileInputStream(new File(filePath))), "utf-8"),
					5 * 1024 * 1024);
			while ((chInt = reader.read()) != -1) {
				System.out.print((char)chInt);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
