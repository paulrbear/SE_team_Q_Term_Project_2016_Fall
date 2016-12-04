public interface Visitor {
	public void visitHeaderNode(HeaderNode hn);
	public void visitItemListNode(ItemListNode in);
	public void visitBlockNode(BlockNode bn);
	public void visitQBlockNode(QuotedBlockNode qbn);
	public void visitHRNode(HorizonRuleNode hrn);
	public void visitBoldToken(BoldToken bt);
	public void visitPlainToken(PlainToken pt);
}