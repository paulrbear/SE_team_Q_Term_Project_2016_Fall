
import java.util.Iterator;

public class PlainVisitor implements Visitor{
	
	static String htmlCode = "<html><head><title></title></head>\n<body>";
	

	public PlainVisitor(){
		
	}
	@Override
	public String getDocument(){
		return htmlCode + "</body>\n</html>";
	}
	public void visitHeaderNode(HeaderNode node) {
		Iterator<Node> it = node.tokens.iterator();
		switch(node.nodeStyle){
		case H1:
			htmlCode = htmlCode + "<h1>";
			while(it.hasNext())	it.next().accept(this);
			htmlCode = htmlCode + "</h1>";
			break;
		case H2:
			htmlCode = htmlCode + "<h2>";
			while(it.hasNext())	it.next().accept(this);
			htmlCode = htmlCode + "</h2>";
			break;
		case H3:
			htmlCode = htmlCode + "<h3>";
			while(it.hasNext())	it.next().accept(this);
			htmlCode = htmlCode + "</h3>";
			break;
		case H4:
			htmlCode = htmlCode + "<h4>";
			while(it.hasNext())	it.next().accept(this);
			htmlCode = htmlCode + "</h4>";
			break;
		case H5:
			htmlCode = htmlCode + "<h5>";
			while(it.hasNext())	it.next().accept(this);
			htmlCode = htmlCode + "</h5>";
			break;
		case H6:
			htmlCode = htmlCode + "<h6>";
			while(it.hasNext())	it.next().accept(this);
			htmlCode = htmlCode + "</h6>";
			break;
		default:
			System.out.println("error!!");
			break;
		}
	}

	public void visitBoldToken(BoldToken bt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void visitPlainToken(PlainToken pt) {
		htmlCode = htmlCode + pt.getString();
		
	}
	
}
