
class BlockNode extends Node{	
	public BlockNode(String str){
		nodeString = str;
		//nodeParse();
	}
	public void accept(Visitor v){
		v.visitBlockNode(this);
	}
}