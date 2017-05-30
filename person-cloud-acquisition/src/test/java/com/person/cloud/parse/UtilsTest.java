package com.person.cloud.parse;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void testUtils() {
		System.out.println("---"+(char) 65279);
	}
	
	@Test
	public void testSplit(){
		String quotesStr = "T-wA;}S}T0000960L0},,F,,CHN,65,6501,,,,,,13999123444,13999123444,,,,,,,,,,0,2011-10-8 8:16:49,10392681";
		String[] strs = quotesStr.split(",");
		int size = strs.length;
		for(int i=0; i<size; i++){
			System.out.println(i+" --- "+strs[i]);
		}
	}
}
