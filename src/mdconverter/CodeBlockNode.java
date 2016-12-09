package mdconverter;

public class CodeBlockNode extends Node {

	public CodeBlockNode(String innerBuffer) {
		nodeString=innerBuffer;
	}

	public String getString() {
		// TODO Auto-generated method stub
		return nodeString;
	}
	public void accept(Visitor v){
		v.visitCodeBlockNode(this);
	}

}
