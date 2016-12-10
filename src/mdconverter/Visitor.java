package mdconverter;
public interface Visitor {
	//Node
	public void visitHeaderNode(HeaderNode hn);
	public void visitItemListNode(ItemListNode in);
	public void visitBlockNode(BlockNode bn);
	public void visitQBlockNode(QuotedBlockNode qbn);
	public void visitListedItem(ListedItem li);
	public void visitCodeBlockNode(CodeBlockNode cbn);
	
	//Token
//	public void visitCodeToken(CodeToken ct);
//	public void visitImageToken(ImageToken it, ImageToken dir);
//	public void visitHTMLToken(HTMLToken ht, HTMLToken dir);
		//Block -> multiple
		public void visitPlainToken(PlainToken pt);
		public void visitBoldToken(BoldToken bt);
		public void visitItalicToken(ItalicToken it);
		//Block -> single
		//public void visitBlockToken(BlockToken bt);
		
	//Document
	public String getDocument();
}