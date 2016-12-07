package org.mdconverter;
public class CodeToken extends Token {

	public CodeToken(String str){
		tokenString = str;
	}
	public void accept(Visitor v){
		v.visitCodeToken(this);
	}
	
}
