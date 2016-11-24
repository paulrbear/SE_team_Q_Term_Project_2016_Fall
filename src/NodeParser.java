import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class NodeParser{

	public enum TokenType{
		PLAIN, EM, STRONG, HTML, LINK, IMAGE, ITEM_LIST, HEADER, Q_BLOCK;	
	}
	
	static Node np;
	private static String tempNodeString;//temp for String received from MDP
	private static String prevString;//previously categorized String
	private static String nextString;//currently compared String
	private static boolean checkStartTrue;//True if the String starts with a Token delmiter
	private static boolean checkEndTrue;//True if the 
	private static String tempCurrString;
	
	//temporarily stores all String split by white space
	//public static ArrayList<String> parseString = new ArrayList<>();
	public static ArrayList<String> parseStringByNL = new ArrayList<>();
	public static ArrayList<String> parseStringByWS = new ArrayList<>();
	
	
	

	////////////////////////Constructor//////////////////////
	public NodeParser(Node np){
		nodeStringParser(np);
	}
	
	/////////////////////////Parser///////////////////////////
	
	//Divides NodeStrings by Token Elements
	public static void nodeStringParser(Node np){

		//Parse NodeString into String array by WS
		parseByWS(np.nodeString);
		System.out.printf("nodeString is parsed into tokens" + parseStringByWS);
		
		while(parseStringByWS.size()!=0){
			parseByWS(nextString);
		}
		while(nodeString!=null){
			tempCurrString = null;
			prevString = nextString;	
			nextString = tempCurrString;
			tokenParseRule(NodeString);
			
			if(tempCurrString == null){
				
			}
			if(tempCurrString != null){
				parseStringByWS.add(tempCurrString);
				System.out.println("line num: " +  tempCurrString.length());
			} 
		
			for(int i = 0; i <tempNodeString.length();i++)
			{
				compareString(parseStringByWS.get(i));
			}
		}
		createToken(String string, String type); //TODO
			
	}
	
	
	
	
	/////////////////////////check if Token///////////////////////////
	
	//Check the start of the String
	private static boolean checkStart(String nextString){
		checkStartRule(tempCurrString);
		
		if(checkStartTrue){
			createToken();
			return true;	
		}else if(!checkStartTrue){
			return false;
		}else{
			System.out.printf("error");
		}
		return checkStartTrue;
					
	}
	
	//Check the end of the String
	private static boolean checkEnd(String nextString){
		checkEndRule(tempCurrString);
		
		if(checkEndTrue){
			return true;	
		}else if(!checkEndTrue){
			return false;
		}else{
			System.out.printf("error");
		}
		return checkEndTrue;
	}

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
			node = new Node();
			np.tokens.add(node);
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

	//Rule for Parsing Node into Tokens
	private static void parseByNL(String NodeString){
		//Parse NodeString received from MD Parser into String Array
		String tempStringArray[] = NodeString.split("\\s+"); 
		//Insert tempStringArray into parseString ArrayList
		parseStringByNL.addAll(Arrays.asList(tempStringArray));
		//String.split("[\\r\\n]+") if you don't want empty lines
	}
	private static void parseByWS(String NodeString){
		//Parse NodeString received from MD Parser into String Array
		String tempStringArray[] = NodeString.split("\\r?\\n");) 
		//Insert tempStringArray into parseString ArrayList
		parseStringByWS.addAll(Arrays.asList(tempStringArray));
		//String.split("[\\r\\n]+") if you don't want empty lines
	}
	
	//Rule for checking if currLine is the Start of a Node
	private static void checkStartRule(String currLine){
		
	}
	
	//Rule for checking if currLine is the End of a Node
	private static void checkEndRule(String currLine){
		
	}	
	
	

	
	
	
	
	
}//end of NodeParser Class
