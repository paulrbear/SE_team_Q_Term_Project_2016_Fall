import java.util.ArrayList;
import java.util.Iterator;

public class Visitor {
	public static String htmlCode ="";
	
	public static void visit(ArrayList<Node> nodes){
		// TODO generate html tag for opening html document
		Iterator<Node> it = nodes.iterator();
		while(it.hasNext()){
			visitNode(it.next());
		}
		// TODO generate html tag for opening html document
	}
	
	protected static void visitNode(Node node){
		visitTokens(node.tokens);
	}
	
	protected static void visitTokens(ArrayList<Token> tokens){
		// TODO generate html tag for opening node
		Iterator<Token> it = tokens.iterator();
		while(it.hasNext()){
			visitToken(it.next());
		}
		// TODO generate html tage for closing node
	}

	protected static void visitToken(Token token) {
		//토큰에 도착했다!!!!!
	}
	
	public Visitor() {
		// TODO Auto-generated constructor stub
	}

}
