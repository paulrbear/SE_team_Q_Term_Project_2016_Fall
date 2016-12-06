import java.util.Iterator;


public class PlainVisitor implements Visitor{
	


	static String htmlCode = "<html><head><title></title></head>\n<body>";
	

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
	public void visitItemListNode(ItemListNode in) {
		Iterator<Node> it = in.tokens.iterator();
		switch(in.nodeStyle) {
		case Ordered:
			htmlCode = htmlCode + "<ol>";
			// need to handle sub item list
			while (it.hasNext()) it.next().accept(this);
			htmlCode = htmlCode + "</ol>";
		case Unordered:
			htmlCode = htmlCode + "<ul>";
			while (it.hasNext()) it.next().accept(this);
			htmlCode = htmlCode + "</ul>";			
		default:
			System.out.println("error!!");
			break;
		}
	}
	public void visitBlockNode(BlockNode bn) {
		Iterator<Node> it = bn.tokens.iterator();
		htmlCode = htmlCode + "<p>";
		while (it.hasNext()) it.next().accept(this);
		htmlCode = htmlCode + "</p>";
	
	}
	public void visitQBlockNode(QuotedBlockNode qbn) {
		Iterator<Node> it = qbn.tokens.iterator();
		htmlCode = htmlCode + "<q>";
		while (it.hasNext()) it.next().accept(this);
		htmlCode = htmlCode + "</q>";
	}
	public void visitHRNode(HorizonRuleNode hrn) {
		Iterator<Node> it = hrn.tokens.iterator();
		htmlCode = htmlCode + "<hr>";
		while (it.hasNext()) it.next().accept(this);
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
	public void visitCodeToken(CodeToken ct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitImageToken(ImageToken it, ImageToken dir) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitHTMLToken(HTMLToken ht, HTMLToken dir) {
		// TODO Auto-generated method stub
		
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
}
