import java.util.ArrayList;

class BlockNode extends Node{
	public enum NodeStyle{
		Plain, Image, Link, Html, Code;
	}
	public NodeStyle nodeStyle;
	
	public BlockNode(String str, NodeStyle style){
		nodeString = str;
		tokens=new ArrayList<Node>();
		setStyle(style);
		//nodeParse();
	}
	public void setStyle(NodeStyle style){
		nodeStyle= style;
	}
	public NodeStyle getStyle(){
		return nodeStyle;
	}
	public void accept(Visitor v){
		v.visitBlockNode(this);
	}
}