import java.util.ArrayList;
import java.util.Iterator;

public class PlainVisitor extends Visitor{

	public PlainVisitor() {
		// TODO Auto-generated constructor stub
	}

	public static void visit(ArrayList<Node> nodes){
		htmlCode = htmlCode + "<html>\n";
		Iterator<Node> it = nodes.iterator();
		while(it.hasNext()){
			visitNode(it.next());
		}/*
		for(int i=0;i<nodes.length;i++){
			cg.nodeCodeGenFront(nodes[i]);
			visitNode(nodes[i]);
			cg.nodeCodeGenBack(nodes[i]);
		}*/
		htmlCode = htmlCode + "\n</html>";
	}
	
	protected static void visitNode(Node node){
		CodeGenerator cg = new CodeGenerator();
		cg.nodeCodeGenFront(node,htmlCode);
		visitTokens(node.tokens);
		cg.nodeCodeGenBack(node,htmlCode);
	}
	
	protected static void visitTokens(ArrayList<Token> tokens){
		
		Iterator<Token> it = tokens.iterator();
		while(it.hasNext()){
			visitToken(it.next());
		}
	}
	
	protected static void visitToken(Token token) {
		CodeGenerator cg = new CodeGenerator();
		cg.tokenCodeGenFront(token,htmlCode);
		htmlCode = htmlCode + token.tokenString ; 
		cg.tokenCodeGenBack(token,htmlCode);
	}
}
