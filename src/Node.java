import java.util.ArrayList;


public class Node {
	// class 이름이 nodeParser이라 가정
	
	public String nodeString;
	
	public ArrayList<Node> tokens;
	
	public Node(){
		
	}
	// send node string to node parser for parsing into tokens.
	public void nodeParse() {
		nodeParser np = new nodeParser(this);
	}
}