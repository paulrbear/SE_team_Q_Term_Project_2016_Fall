package mdconverter;
import java.util.ArrayList;

public class ItalicToken extends Token {
	
	public ItalicToken(String str){
		nodeString = str;
		tokens=new ArrayList<Node>();
		nodeParse();
	}
	public void accept(Visitor v){
		v.visitItalicToken(this);
	}
	
}
