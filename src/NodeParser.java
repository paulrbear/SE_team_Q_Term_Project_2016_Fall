import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class NodeParser{

	public enum TokenType{
		PLAIN, EM, STRONG, HTML, LINK, IMAGE, ITEM_LIST, HEADER, Q_BLOCK;	
	}
	
	private static String buffer;
	private static String current;
	private static String leftover;
	private static String[] spl;
	public static ArrayList<String> parseString = new ArrayList<>();
	
	private static boolean bold;
	private static boolean italic;
	
	
	////////////////////////Constructor//////////////////////
	public NodeParser(Node np){
		if(np.nodeString!=null){
			nodeStringParser(np);	
		}else if(np.nodeString==null){
			System.out.printf("nodeString is empty (nodeStringParser)");
		}	
	}
	
	/////////////////////////Parser///////////////////////////
	public void nodeStringParser(Node np){
		String ns = np.nodeString;
		
		while(ns!=null){
			if(ns.matches("^/*/*.*/*/*")){
				isBoldItalic(ns);
				buffer = spl[0];
				createToken(buffer, TokenType.PLAIN);
				current = spl[1];
				createToken(current, TokenType.STRONG);
				leftover = spl[2];
				ns = leftover;
			}
		
		}//end of while
	}//end of nSP
	
	
	
	
	/////////////////////////RULE///////////////////////////
	/*
	_*text*_
	*_text_*
	_*text_*
	*_text*_
	one_*text*_one
	one*_text_*one
	one_*text_*one
	one*_text*_one

	text
	text
	*text*
	_text_
	one_text_one
	onetextone
	one_text_one
	one_text_one
	*/
	/*
	//EM & STRONG 
		if(ns.startsWith("*")&&ns.contains("* ")){
			if(ns.startsWith("**")&&ns.contains("** ")){
				spl = ns.split("** ", 2);
				buffer = spl[0].replace("*", "");
				leftover = spl[1];
				createToken(buffer, TokenType.STRONG);
			}else{
				spl = ns.split("* ", 2);
				buffer = spl[0].replace("*", "");
				leftover = spl[1];
				createToken(buffer, TokenType.EM);
			}
		}else if(ns.endsWith("*")&&ns.contains(" *")){
			if(ns.endsWith("**")&&ns.contains(" **")){
				spl = ns.split(" **", 2);
				buffer = spl[spl.length -2];
				leftover = spl[spl.length-1].replace("*", "");
				createToken(buffer, TokenType.STRONG);
			}else{
				spl = ns.split(" *", 2);
				buffer = spl[spl.length -2];
				leftover = spl[spl.length-1].replace("*", "");
				createToken(buffer, TokenType.EM);
			}
		}
		if(ns.startsWith("_")&&ns.contains("_ ")){
			if(ns.startsWith("__")&&ns.contains("__ ")){
				spl = ns.split("__ ", 2);
				buffer = spl[0].replace("_", "");
				leftover = spl[1];
				createToken(buffer, TokenType.STRONG);
			}else{
				spl = ns.split("_ ", 2);
				buffer = spl[0].replace("_", "");
				leftover = spl[1];
				createToken(buffer, TokenType.EM);
			}
		}else if(ns.endsWith("_")&&ns.contains(" _")){
			if(ns.endsWith("__")&&ns.contains(" __")){
				spl = ns.split(" __", 2);
				buffer = spl[spl.length -2];
				leftover = spl[spl.length-1].replace("_", "");
				createToken(buffer, TokenType.STRONG);
			}else{
				spl = ns.split(" _", 2);
				buffer = spl[spl.length -2];
				leftover = spl[spl.length-1].replace("_", "");
				createToken(buffer, TokenType.EM);
			}
		}
		if(ns.contains(" *")&&ns.contains("* ")){
			if(ns.contains(" **")&&ns.contains("** ")){
				
			}
		}
	 */
	public static String[] isBoldItalic(String ns){
		spl = ns.split("**",3);
		return spl;		
	}
	
	///////////////////////////MISC/////////////////////
	
	//Flush method. Empty all temp.
		public static void initializeAll()
		{
			
		}

	//Create new Token and add to Node Array
	public static void createToken(String ts, TokenType type)
	{
		Node node;
		if(type.ordinal()==0){
			node = new Token();
			node.tokens.add(node);
		}else if(type.ordinal()==1){
			node = new ItalicToken();
			node.tokens.add(node);
		}else if(type.ordinal()==1){
			node = new BoldToken();
			node.tokens.add(node);
		}else{
			System.out.printf("error in createToken");
		}
		
		initializeAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//end of NodeParser Class
