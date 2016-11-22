
public class Visitor {
	
	public static void visit(Node[] nodes){
		for(int i=0;i<nodes.length;i++){
			visitNode(nodes[i]);
		}
	}
	
	private static void visitNode(Node node){
		visitTokens(node.tokens);
	}
	
	private static void visitTokens(Node[] tokens){
		for(int i=0;i<tokens.length;i++){
			visitToken(tokens[i]);
		}
	}

	private static void visitToken(Node node) {
		// TODO Auto-generated method stub	
	}
	
	public Visitor() {
		// TODO Auto-generated constructor stub
	}

}
