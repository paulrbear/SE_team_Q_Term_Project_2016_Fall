public interface Visitor {
	public void visitHeaderNode(HeaderNode hn);
	public void visitBoldToken(BoldToken bt);
	public void visitPlainToken(PlainToken pt);
	public String getDocument();
}
