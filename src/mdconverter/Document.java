package mdconverter;
import java.util.ArrayList;
import java.util.Iterator;

public class Document {

	
	public ArrayList<Node> nodes = new ArrayList<>();

	

	public void accept(Visitor v){
		Iterator<Node> it = nodes.iterator();
		while(it.hasNext())	it.next().accept(v);
	}
}
