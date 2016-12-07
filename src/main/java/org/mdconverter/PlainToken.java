package main;
public class PlainToken extends Token {
	
	public PlainToken(String str){
		tokenString = str;
	}
	public void accept(Visitor v){
		v.visitPlainToken(this);
	}

}
