import java.util.ArrayList;

public class Node {
	
	// node object와 mapping 될 node type들을 나열한 Enum list
	public enum NodeType{
		HEADER, LIST, Q_BLOCK, BLOCK, HORIZONTAL_, ORDER, UNORDER, ITEM_LIST;	
	}
	
	//ATTRIBUTES!!!!!
	static ArrayList<Token> tokens; // token list
	private NodeType nodeType;	// node의 type
	public String tempS; // divideToken method의 parameter로 들어가서 update될 string이다.
	
	public void setNodeType(NodeType nt)
	{
		this.nodeType = nt;
	}
	
	public NodeType getNodeType()
	{
		return nodeType;
	}
}