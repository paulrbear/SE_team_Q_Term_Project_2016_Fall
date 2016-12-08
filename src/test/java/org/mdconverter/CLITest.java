package org.mdconverter;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.*;

import org.mdconverter.CLI;

public class CLITest {
	
	String[] input;
	
	
	//$ test.md -p test
	@Before
	public void initialize(){
		input[0] = "test.md";
		input[1] = "-p";
		input[2] = "test";
		//CLI cli = new CLI(input);
	}
	@Test
	public void inputParserTest(){	
		CLI cli = mock(CLI.class);
		cli.CLI(input);
		
		
	}
	@After
	public void deinitialize(){
		input = null;
	}
	
	
	
	
}
//check if method was called:
//verify(mockedObject, times(1)).methodToValidate();