package mdconverter;


class HorizonRuleNode extends Node{	
	public HorizonRuleNode(){
	}
	public void accept(Visitor v){
		v.visitHRNode(this);
	}
}