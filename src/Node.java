import java.util.ArrayList;

public class Node {
	
	// node object와 mapping 될 node type들을 나열한 Enum list
	public enum NodeType{
		// TO-DO 
			//implement the details
			//add more types (더 있으면..)
		HEADER, LIST, Q_BLOCK, BLOCK, HORIZONTAL_, ORDER, UNORDER, ITEM_LIST;	
	    static {
	    }
	}
	
	//ATTRIBUTES!!!!!
	static ArrayList<Token> tokens; // token 리스트
	private NodeType nodeType;	// node의 type
	public String tempS; // divideToken method의 parameter로 들어가서 update될 string이다.
	
	
	
	//OPERATIONS!!!!!!! 범환오빠가 하실 부분!! 
	//  필요에 따라 parameter나 return type 알아서 바꾸시면 될 것 같아요!!ㅋㅋㅋ
	public void divideToken(String s) // string s는 계속 업데이트 됨. 
	{
	
		
		tempS = ""; //새로운 string으로 업데이트: 최근에 생성된 token의 string이 끝 지점부터 전체 string 끝까지
	}
	
	public void setNodeType(NodeType nt)
	{
		this.nodeType = nt;
	}
	
	public NodeType getNodeType()
	{
		return nodeType;
	}

	public Node createNode(String s)	// Node object 생성하는 op. 
	{
		//TO-DO 수정할것*
		return new Node(); 	
	}

	
	
	public static void main(String[] args)
	{
		
		Node node = new Node();
	}

}
