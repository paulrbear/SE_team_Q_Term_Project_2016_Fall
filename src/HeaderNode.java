
class HeaderNode extends Node{
	public enum NodeStyle{
		H1,H2,H3,H4,H5,H6;
	}
	public NodeStyle nodeStyle;
	
	public HeaderNode(String str,NodeStyle style){
		nodeString = str;
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
		v.visitHeaderNode(this);
	}

}