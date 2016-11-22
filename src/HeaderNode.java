class HeaderNode extends Node{
	//  visitor?
	public void accept(Visitor v) {
		v.visitNode(this);
	}
}