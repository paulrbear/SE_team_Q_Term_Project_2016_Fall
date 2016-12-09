package org.mdconverter;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class dummyTest {
	@Test
	public void tempTest(){
		String[] expectedOutput = {"apple", "mango", "grape"};
	    String[] methodOutput = {"apple", "mango", "grape"};
	    
	    assertArrayEquals(expectedOutput, methodOutput);
	}
}
