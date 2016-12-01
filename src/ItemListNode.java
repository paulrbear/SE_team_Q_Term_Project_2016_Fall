
class ItemListNode extends Node{
	public enum NodeStyle{
		Ordered, Unordered;
	}
	public NodeStyle nodeStyle;
	
	public ItemListNode(String str,NodeStyle style){
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
		v.visitItemListNode(this);
	}

}