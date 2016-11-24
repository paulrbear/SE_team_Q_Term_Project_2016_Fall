import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class NodeParser{

	public enum TokenType{
		PLAIN, EM, STRONG, HTML, LINK, IMAGE, ITEM_LIST, HEADER, Q_BLOCK;	
	}
	
	//temporarily stores all String split by white space
	//public static ArrayList<String> parseString = new ArrayList<>();
	public static ArrayList<String> parseString = new ArrayList<>();
	
	
	////////////////////////Constructor//////////////////////
	public NodeParser(Node np){
		nodeStringParser(np);
	}
	
	/////////////////////////Parser///////////////////////////
	
	public void nodeStringParser(Node np){
		
	}
	
	
	
	
	/////////////////////////check if Token///////////////////////////
	
	///////////////////////////MISC/////////////////////
	
	//Flush method. Empty all temp.
		public static void initializeAll()
		{
			checkStartTrue = false;
			checkEndTrue = false;
			tempNodeString = null;
			tempCurrString = null;
			nextString = null;
			prevString = null;
		}

	//Create new Token and add to Node Array
	public static void createToken(String ts, TokenType type)
	{
		Node node;
		if(type.ordinal()==0){
			//Token token = new Token();
			node = new Italic();
			node.tokens.add(node);
		}
		
		/*
		else if(type.ordinal()==1){
			token = new Token();
			np.tokens.add(token);
		}else if(type.ordinal()==1){
			token = new Token();
			np.tokens.add(token);
		}else{
			System.out.printf("error in createToken");
		}
		*/
		
		initializeAll();
	}
	
	/////////////////////////Rule///////////////////////////

	
	
	
}//end of NodeParser Class
