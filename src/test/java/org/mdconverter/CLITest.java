package org.mdconverter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.List;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Iterator;

import org.junit.Test;
import org.junit.After;
//import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;
import org.mdconverter.CLI;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class CLITest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	@Test
	public void asdfffga(){		
		assertTrue(CLI.orderCheck(1,-1,1));
	}
	@Test
	public void asdfffgsa(){		
		assertTrue(CLI.orderCheck(1,1,1));
	}
//	String[] input;
	/*
	String inputs = ""
					+"###### this is test code!/n" 
					
					+"1. asdf"
					+"* asdf"
					+"* dfha"
					+"asdffhdsdfassd"
					+"asd*em1*sdf"
					+"asdf_em2_asdf"
					+"asdfkd**this is strong test1**a"
					+"sdfad__strong2__asdff/n"
					
					+"> quoted"
					+"> quoted33"
					+"> quoted6134"
					+"> quoted5"
					+"> quoted234"
					+"> quoted12355123/n"
					
					+"<table> <tr>"
					+"<td>Foo</td> </tr>"
					+"</table>"
					
					+"/n# h1"
					+"## h2 "
					+"different style of h1"
					+"=====/n";
		*/			
	/////////////////////////////////////////////////////////
	
	@Test
	public void tempTest(){
		String[] expectedOutput = {"apple", "mango", "grape"};
	    String[] methodOutput = {"apple", "mango", "grape"};
	    
	    assertArrayEquals(expectedOutput, methodOutput);
	}
	
	@Test
	public void MockitoTest(){
		List mockList = mock(List.class);
		mockList.add("one");
		mockList.clear();
		
		verify(mockList).add("one");
		verify(mockList).clear();
	}
	
	@Test
	 public void iterator_will_return_hello_world(){
	  //arrange
	  Iterator i=mock(Iterator.class);
	  when(i.next()).thenReturn("Hello").thenReturn("World");
	  //act
	  String result=i.next()+" "+i.next();
	  //assert
	  assertEquals("Hello World", result);
	 }
	
	@Test
	public void with_arguments(){
	 Comparable c=mock(Comparable.class);
	 when(c.compareTo("Test")).thenReturn(1);
	 assertEquals(1,c.compareTo("Test"));
	}
	//////////////////////////////////////////////////////////

	
////////////////////START OF cliCheckTest/////////////////////
/*
	@Before
	public void init(){
		input = null;
	}

	@Mock
	private Help help;
	//Class under test
	private CLI cli;
	
	@Before
	public void Dependency(){
		MockitoAnnotations.initMocks(this);
		String[] input = {""};
		cli = new CLI(input);
	}
	@Test
	public void cliCheckTest(){
		String[] input = {""};
		cli.cliCheck(input);
		verify(help).help();
	}
	w
	@Test
	public void cliCheckTest_WithNoInput(){	
		
		//input[0] = "";
		String[] input = {""};
		
		CLI cli = new CLI(input);
	
		Help help = mock(Help.class);
		cli.cliCheck(input);
		verify(help, times(1)).help();
	}
	
	//////////////////////////////////////

	@Test
	public void cliCheckTest_Help(){	
		
		//input[0] = "help";
		String[] input = {"help"};
		
		CLI cli = new CLI(input);
	
		Help help = mock(Help.class);
		cli.cliCheck(input);
		verify(help, times(1)).help();
	}
*/
	
	//////////////////////////////////////

	
/*	
	@Test
	public void cliCheckTest_Normal(){	
		
		//input[0] = "test.md";
		//input[1] = "-p";
		//input[2] = "test";
		
		String[] input = {"test.md","-p","test"};

		CLI mock = mock(CLI.class);
		mock.cliCheck(input);
//		CLI cli = new CLI(input);
		//CLI cli = new CLI(input);
	
		//cli.cliCheck(input);
		//verify(cli, times(1)).inputParser(input);
	}
*/
	
/////////////////////END OF cliCheckTest/////////////////////	

	/*
/////////////////////START OF inputParserTest/////////////////////
	

	@Test
	public void inputParserTest_CheckMD_TRUE_CheckOption_TRUE(){
	
		//input[0] = "test.md";
		//input[1] = "-p";
		//input[2] = "test";
		String[] input = {"test.md","-p","test"};
		
		CLI cli = new CLI(input);
		File file = mock(File.class);
		cli.inputParser(input);	
		verify(file, times(1)).isFile();
		verify(cli, times(1)).setStyle(input[0]);
			
	}

/////////////////////////////////////////////////////////////////
	

	@Test
	public void inputParserTest_CheckMD_FALSE_CheckOption_FALSE(){

		//input[0] = "";
		//input[1] = "";
		//input[2] = "";
		String[] input = {"","",""};
		
		CLI cli = new CLI(input);
		File file = mock(File.class);
		cli.inputParser(input);	
		verify(file, times(0)).isFile();
		verify(cli, times(0)).setStyle(input[0]);
			
	}

	
/////////////////////////////////////////////////////////////////

	@Test
	public void inputParserTest_CheckOutPut_TRUE(){

		//input[0] = "test.md";
		//input[1] = "-p";
		//input[2] = "test";
		String[] input = {"test.md","-p","test"};
		
		CLI cli = new CLI(input);
		File file = mock(File.class);
		cli.inputParser(input);	
		verify(file, times(0)).isFile();
		verify(cli, times(0)).setStyle(input[0]);
			
	}
	*/
/////////////////////////////////////////////////////////////////

	
	
	
/////////////////////END OF inputParserTest/////////////////////
/*
	@After
	public void deinit(){
		input = null;
	} 
*/
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
	

	
	
	
	
	
	
	
	
	
	
	
	
}
//check if method was called:
//verify(mockedObject, times(1)).methodToValidate();