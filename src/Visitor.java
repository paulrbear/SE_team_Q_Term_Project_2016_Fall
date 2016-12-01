public interface Visitor {
	public void visitHeaderNode(HeaderNode hn);
	public void visitBoldToken(BoldToken bt);
	public void visitPlainToken(PlainToken pt);
	public void visitItemListNode(ItemListNode in);
	public void visitBlockNode(BlockNode bn);
	public void visitQBlockNode(QuotedBlockNode qbn);
}
