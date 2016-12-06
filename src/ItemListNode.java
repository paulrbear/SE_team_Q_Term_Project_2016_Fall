import java.util.ArrayList;

class ItemListNode extends Node{
	public enum NodeStyle{
		Ordered, Unordered;
	}
	public NodeStyle nodeStyle;
	
	public ItemListNode(String str,NodeStyle style){
		nodeString = str;
		tokens=new ArrayList<Node>();
		setStyle(style);
		//nodeParse();
	}
	public ItemListNode(String ns) {
		// TODO Auto-generated constructor stub
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