import java.util.ArrayList;

class QuotedBlockNode extends Node{	
	public QuotedBlockNode(String str){
		nodeString = str;
		tokens=new ArrayList<Node>();
		nodeParse();
	}
	public void accept(Visitor v){
		v.visitQBlockNode(this);
	}
}