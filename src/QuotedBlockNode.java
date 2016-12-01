
class QuotedBlockNode extends Node{	
	public QuotedBlockNode(String str){
		nodeString = str;
		//nodeParse();
	}
	public void accept(Visitor v){
		v.visitQBlockNode(this);
	}
}