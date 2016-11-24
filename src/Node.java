import java.util.ArrayList;


public class Node implements nodeInterface{
	// class 이름이 nodeParser이라 가정
	
	public String nodeString;
	
	public ArrayList<Node> tokens;
	
	public Node(){
		
	}
	// send node string to node parser for parsing into tokens.
	public void nodeParse() {
		nodeParser np = new nodeParser(this);
	}
	
	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
}