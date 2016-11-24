
class HeaderNode extends Node{
	public enum NodeStyle{
		H1,H2,H3,H4,H5,H6;
	}
	public NodeStyle nodeStyle;
	
	public HeaderNode(String str,NodeStyle style){
		nodeString = str;
		nodeStyle = style;
		nodeParse();
	}

}