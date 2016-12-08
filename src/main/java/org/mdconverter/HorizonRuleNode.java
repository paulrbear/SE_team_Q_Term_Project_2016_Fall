import java.util.ArrayList;

class HorizonRuleNode extends Node{	
	public HorizonRuleNode(String str){
		nodeString = str;
		tokens=new ArrayList<Node>();
		//nodeParse();
	}
	public void accept(Visitor v){
		v.visitHRNode(this);
	}
}