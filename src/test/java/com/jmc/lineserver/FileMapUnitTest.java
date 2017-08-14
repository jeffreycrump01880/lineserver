package com.jmc.lineserver;

import org.junit.Assert;
import org.junit.Test;

public class FileMapUnitTest {

	@Test
	public void testInput2() throws Exception {
		
		FileMap fm = new FileMap();
		fm.parse("src/test/resources/salsify-input-2.txt");
		
		String[] expected = new String[] {
				"aaa",
				"bb",
				"ccccccccccccc",
				"",
				"ddd",
				"eeeee",
				"f"
		};
		
		for (int i = 0; i < expected.length; i++) {
			Assert.assertEquals(expected[i], fm.getLine(i));
		}
	}
	
	@Test
	public void testInput1() throws Exception {
		
		FileMap fm = new FileMap();
		fm.parse("src/test/resources/salsify-input-1.txt");
		
		String[] expected = new String[] {
				"",
				"line 2 after a blank line",
				"line 4 is blank",
				""
		};
		
		for (int i = 0; i < expected.length; i++) {
			Assert.assertEquals(expected[i], fm.getLine(i));
		}
	}
}
