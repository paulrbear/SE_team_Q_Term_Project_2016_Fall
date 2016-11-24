public class Node {
	// class 이름이 nodeParser이라 가정
	nodeParser np = new nodeParser();

	public String nodeString = "";

	// visitor가 node class 방문
	public void accept(Visitor v) {}
	
	// node object와 mapping 될 node type들을 나열한 Enum list
	public enum NodeType {
		HEADER, LIST, QBLOCK, BLOCK, HORIZONTAL, ITEM_LIST, PLAIN;	
	}

	//ATTRIBUTES!!!!!
	private NodeType nodeType;	// node의 type
	public String tempS; // divideToken method의 parameter로 들어가서 update될 string이다.
	
	public void setNodeType(NodeType nt) {
		this.nodeType = nt;
	}
	
	public NodeType getNodeType() {
		return nodeType;
	}
	public String getString(String s) {
		nodeString = s;
		return nodeString;
	}

	// send node string to node parser for parsing into tokens.
	public void nodeParse() {
		np.nodeParse(nodeString);
	}
}