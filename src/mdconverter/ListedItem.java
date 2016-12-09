package mdconverter;
import java.util.ArrayList;

public class ListedItem extends Node {
	public ListedItem(String str){
		nodeString = str;
		tokens=new ArrayList<Node>();
		nodeParse();
	}
	public void accept(Visitor v){
		v.visitListedItem(this);
	}
}
