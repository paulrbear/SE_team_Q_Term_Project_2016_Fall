package mdconverter;
import java.util.Iterator;


public class PlainVisitor implements Visitor{
	


	static String htmlCode = "<html>\n<head><title></title></head>\n<body>\n";
	

	public PlainVisitor(){
		
	}

	public String getDocument(){
		return htmlCode + "</body>\n</html>";
	}

	@Override
	public void visitHeaderNode(HeaderNode hn) {
		Iterator<Node> it = hn.tokens.iterator();
		switch(hn.nodeStyle){
		case H1:
			htmlCode = htmlCode + "<h1>";
			while(it.hasNext())	it.next().accept(this);
			htmlCode = htmlCode + "</h1>\n";
			break;
		case H2:
			htmlCode = htmlCode + "<h2>";
			while(it.hasNext())	it.next().accept(this);
			htmlCode = htmlCode + "</h2>\n";
			break;
		case H3:
			htmlCode = htmlCode + "<h3>";
			while(it.hasNext())	it.next().accept(this);
			htmlCode = htmlCode + "</h3>\n";
			break;
		case H4:
			htmlCode = htmlCode + "<h4>";
			while(it.hasNext())	it.next().accept(this);
			htmlCode = htmlCode + "</h4>\n";
			break;
		case H5:
			htmlCode = htmlCode + "<h5>";
			while(it.hasNext())	it.next().accept(this);
			htmlCode = htmlCode + "</h5>\n";
			break;
		case H6:
			htmlCode = htmlCode + "<h6>";
			while(it.hasNext())	it.next().accept(this);
			htmlCode = htmlCode + "</h6>\n";
			break;
		default:
			break;
		}
	}
	public void visitItemListNode(ItemListNode in) {
		Iterator<Node> it = in.tokens.iterator();
		switch(in.nodeStyle) {
		case Ordered:
			htmlCode = htmlCode + "<ol>";
			// need to handle sub item list
			while (it.hasNext()) it.next().accept(this);
			htmlCode = htmlCode + "</ol>\n";
			break;
		case Unordered:
			htmlCode = htmlCode + "<ul>";
			System.out.println("hello");
			while (it.hasNext()) it.next().accept(this);
			htmlCode = htmlCode + "</ul>\n";
			break;
		default:
			break;
		}
	}
	public void visitBlockNode(BlockNode bn) {
		Iterator<Node> it = bn.tokens.iterator();
		htmlCode = htmlCode + "<p>";
		while (it.hasNext()) it.next().accept(this);
		htmlCode = htmlCode + "</p>\n";
	
	}
	public void visitQBlockNode(QuotedBlockNode qbn) {
		Iterator<Node> it = qbn.tokens.iterator();
		htmlCode = htmlCode + "<q>";
		while (it.hasNext()) it.next().accept(this);
		htmlCode = htmlCode + "</q>\n";
	}

	public void visitBoldToken(BoldToken bt) {
		Iterator<Node> it = bt.tokens.iterator();
		htmlCode = htmlCode + "<strong>";
		while (it.hasNext()) it.next().accept(this);
		htmlCode = htmlCode + "</strong>";
	}
	public void visitPlainToken(PlainToken pt) {
		htmlCode = htmlCode + pt.getString();
	}

	@Override
	public void visitItalicToken(ItalicToken it) {
		Iterator<Node> iter = it.tokens.iterator();
		htmlCode = htmlCode + "<em>";
		while (iter.hasNext()) iter.next().accept(this);
		htmlCode = htmlCode + "</em>";
		
	}

	@Override
	public void visitListedItem(ListedItem li) {
		Iterator<Node> it = li.tokens.iterator();
		htmlCode = htmlCode + "<li>";
		while (it.hasNext()) it.next().accept(this);
		htmlCode = htmlCode + "</li>";
		
	}

	@Override
	public void visitCodeBlockNode(CodeBlockNode cbn) {
		htmlCode = htmlCode +"<pre><code>";
		htmlCode = htmlCode +cbn.getString();
		htmlCode = htmlCode +"</code></pre>\n";
	}
}
