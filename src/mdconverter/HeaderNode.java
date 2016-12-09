package mdconverter;
import java.util.ArrayList;

class HeaderNode extends Node{
	public enum NodeStyle{
		H1, H2, H3, H4, H5, H6;
	}
	public NodeStyle nodeStyle;
	public int headerSize;
	public HeaderNode(String str, NodeStyle style){
		nodeString = str;
		tokens=new ArrayList<Node>();
		setStyle(style);
		nodeParse();
	}
	public HeaderNode(String str, int style){
		nodeString = str;
		tokens=new ArrayList<Node>();
		setStyle(style);
		nodeParse();
	}
	public void setStyle(NodeStyle ns){
		nodeStyle = ns ; 
	}
	public void setStyle(int style){
		switch(style){
		
		case 1:
			nodeStyle = NodeStyle.H1;
			break;
		case 2:
			nodeStyle = NodeStyle.H2;
			break;
		case 3:
			nodeStyle = NodeStyle.H3;
			break;
		case 4:
			nodeStyle = NodeStyle.H4;
			break;
		case 5:
			nodeStyle = NodeStyle.H5;
			break; 
		case 6:
			nodeStyle = NodeStyle.H6;
			break;
		default:
			break;
		}
	}
	public NodeStyle getStyle(){
		return nodeStyle;
	}
	public void accept(Visitor v){
		v.visitHeaderNode(this);
	}
}