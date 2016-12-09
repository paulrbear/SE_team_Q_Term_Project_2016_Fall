package org.mdconverter;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import org.mdconverter.MDParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MDParserTest {
	
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
	@Test
	public void comparePNTest(){
		MDParser mdparser = new MDParser();
		
		
	}

	
}

/*
public class Calculator {
  public int evaluate(String expression) {
    int sum = 0;
    for (String summand: expression.split("\\+"))
      sum += Integer.valueOf(summand);
    return sum;
  }
}

public class CalculatorTest {
  @Test
  public void evaluatesExpression() {
    Calculator calculator = new Calculator();
    int sum = calculator.evaluate("1+2+3");
    assertEquals(6, sum);
  }
}

*/