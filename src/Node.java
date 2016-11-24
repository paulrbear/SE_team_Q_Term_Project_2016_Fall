import java.util.ArrayList;

public class Node {
	// class 이름이 nodeParser이라 가정
	nodeParser np = new nodeParser();

	public String nodeString;
	
	public ArrayList<Node> tokens;
	
	public Node(String str,NodesStyle nodeStyle){
		nodeString=str;
	}
	// send node string to node parser for parsing into tokens.
	public void nodeParse() {
		np.nodeParse(nodeString);
	}
}