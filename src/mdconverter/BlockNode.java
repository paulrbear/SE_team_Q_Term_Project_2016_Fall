package mdconverter;
import java.util.ArrayList;

class BlockNode extends Node{
	
	public BlockNode(String str){
		nodeString = str;
		tokens=new ArrayList<Node>();
		nodeParse();
	}

	public void accept(Visitor v){
		v.visitBlockNode(this);
	}
}