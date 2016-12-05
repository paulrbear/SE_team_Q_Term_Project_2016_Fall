import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.IOException;



public class NodeParser{

	public enum TokenType{
		PLAIN, EM, STRONG, HTML, LINK, IMAGE, ITEM_LIST, HEADER, Q_BLOCK;	
	}
	
//	private static String buffer;
//	private static String current;
//	private static String leftover;
	private static String[] spl;
	public static ArrayList<String> parseString = new ArrayList<>();
	
//	private static boolean bold;
//	private static boolean italic;
	
	
	////////////////////////Constructor//////////////////////
	public NodeParser(Node np){
		if(np.nodeString!=null){
			nodeStringParser(np);	
		}else if(np.nodeString==null){
			System.out.printf("nodeString is empty (nodeStringParser)");
		}	
	}
	
	/////////////////////////Parser///////////////////////////
	public static void nodeStringParser(Node np){
		String ns = np.nodeString;
		while(!ns.isEmpty()){
			StringReader sReader = new StringReader(ns);
			BufferedReader bReader = new BufferedReader(sReader);
			try {
				String l= ""; 
				l = bReader.readLine();
				if(l.matches("[*]{2}[A-Z]*[*]{2}")){
				Pattern pattern = Pattern.compile("[*]{2}[A-Z]*[*]{2}");
				Matcher match = pattern.matcher(l);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			createToken(np, ns, TokenType.PLAIN);
			ns="";
		}
	}
	
	
	
	
	public static String[] isBoldItalic(String ns){
		spl = ns.split("**",3);
		return spl;		
	}
	public static void initializeAll(){
			
	}

	//Create new Token and add to Node Array
	public static void createToken(Node np, String ts, TokenType type)
	{
		Token token;
		if(type.ordinal()==0){
			token = new PlainToken(ts);
			np.tokens.add(token);
		}else if(type.ordinal()==1){
			token = new ItalicToken(ts);
			np.tokens.add(token);
		}else if(type.ordinal()==1){
			token = new BoldToken(ts);
			np.tokens.add(token);
		}else{
			System.out.printf("error in createToken");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//end of NodeParser Class
