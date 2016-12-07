package main;
public class Token extends Node{
	
	String tokenString;
	String tokenDir;
	
	public Token(){
	}
	
	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
	public String getString(){
		return tokenString;
	}
}
