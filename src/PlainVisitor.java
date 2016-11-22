import java.util.ArrayList;
import java.util.Iterator;

public class PlainVisitor extends Visitor{

	public PlainVisitor() {
		// TODO Auto-generated constructor stub
	}

	public static void visit(Node[] nodes){
		htmlCode = htmlCode + "<html>\n";
		CodeGenerator cg = new CodeGenerator();
		for(int i=0;i<nodes.length;i++){
			cg.nodeCodeGenFront(nodes[i]);
			visitNode(nodes[i]);
			
		}
		htmlCode = htmlCode + "\n</html>";
	}
	
	protected static void visitTokens(ArrayList<Token> tokens){
		// TODO generate html tag for opening node
		Iterator<Token> it = tokens.iterator();
		while(it.hasNext()){
			visitToken(it.next());
		}
		// TODO generate html tag for closing node
	}
	
	protected static void visitToken(Token token) {
		//TODO generate html tag for opening token
		//TODO put strings between 
		//TODO generate html tag for closing token
	}
}
