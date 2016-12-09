package org.mdconverter;

import org.junit.Test;

import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;



public class NodeParserTest {
	
	
	@Test
	public void tempTest(){
		NodeParser np = new NodeParser();
		Node node = new Node();
		NodeParser.createToken(node, anyString(), NodeParser.TokenType.EM);
		verify(np, times(1)).Success();
		NodeParser.createToken(node, anyString(), NodeParser.TokenType.HEADER);
		verify(np, times(1)).Success();
		NodeParser.createToken(node, anyString(), NodeParser.TokenType.HTML);
		verify(np, times(1)).Success();
		NodeParser.createToken(node, anyString(), NodeParser.TokenType.IMAGE);
		verify(np, times(1)).Success();
		NodeParser.createToken(node, anyString(), NodeParser.TokenType.UNORDERED_LIST);
		verify(np, times(1)).Success();
		NodeParser.createToken(node, anyString(), NodeParser.TokenType.LISTED_ITEM);
		verify(np, times(1)).Success();
		NodeParser.createToken(node, anyString(), NodeParser.TokenType.LINK);
		verify(np, times(1)).Success();
		NodeParser.createToken(node, anyString(), NodeParser.TokenType.PLAIN);
		verify(np, times(1)).Success();
		NodeParser.createToken(node, anyString(), NodeParser.TokenType.Q_BLOCK);
		verify(np, times(1)).Success();
		NodeParser.createToken(node, anyString(), NodeParser.TokenType.STRONG);
		verify(np, times(1)).Failed();
	}
	
	/*	
	public void tempTest(){
		String[] expectedOutput = {"apple", "mango", "grape"};
	    String[] methodOutput = {"apple", "mango", "grape"};
	    
	    assertArrayEquals(expectedOutput, methodOutput);
	}
	
	@Test
	public void CTT_EM(){
		NodeParser np = new NodeParser();
		Node node = new Node();
		assertTrue(np.parser("test string", node));
		assertEquals(1, NodeParser.TokenType.EM);
	}
	
	@Test
	public void cTT_EM(){
		Node node = Mockito.mock(Node.class);
		NodeParser np = new NodeParser(null);

		String s = "test string";
		
	    Mockito.doCallRealMethod().when(np).createToken(node, s, TokenType.PLAIN);
	    Mockito.doCallRealMethod().when(np).addToken(node, s);
	
	    np.confirm().onConfirm();
	
	    Mockito.verify(np).addToken(node, s);
	}
	
	@Test
	public void cTT_EM(){
		NodeParser service = new NodeParser(null);
		NodeParser spy = Mockito.spy(service);
		Node np = new Node();
		String s = "test string";
		spy.createToken(np, s, null);
		verify(spy).;
		
	}

	@Test
	public void createTokenTest_EM(){
		String s = "test string";
		Node node = Mockito.mock(Node.class);
		NodeParser np = new NodeParser(null);
		np.createToken(np, s, TokenType.type);
		//np.createToken(node, s, TokenType.PLAIN);
		Mockito.verify(node, times(1)).tokens.add(node);
	}
	*/
	@Test
	public void createTokenTest_UL(){
		String[] expectedOutput = {"apple", "mango", "grape"};
	    String[] methodOutput = {"apple", "mango", "grape"};
	    
	    assertArrayEquals(expectedOutput, methodOutput);
		
	}
}
