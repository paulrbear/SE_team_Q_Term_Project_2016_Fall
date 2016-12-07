package org.mdconverter;
import java.util.ArrayList;

public class BoldToken extends Token {
	
	public BoldToken(String str){
		nodeString = str;
		tokens=new ArrayList<Node>();
		nodeParse();
	}
	public void accept(Visitor v){
		v.visitBoldToken(this);
	}

}
