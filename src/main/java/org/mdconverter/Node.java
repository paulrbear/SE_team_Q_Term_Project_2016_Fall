package main;
import java.util.ArrayList;


public class Node implements nodeInterface{
	
	
	public String nodeString;
	
	public ArrayList<Node> tokens;
	
	public Node(){
		
	}
	// send node string to node parser for parsing into tokens.
	public void nodeParse() {
		NodeParser.nodeStringParser(this);
	}
	public void addToken(Token token){
		tokens.add(token);
	}
	
	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
}