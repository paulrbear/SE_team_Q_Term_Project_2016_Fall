
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class MDParser{
	
// ATTRIBTUES

	public static enum NodeType
	{
		PLAIN, HEADER, UL_ITEM, OL_ITEM, Q_BLOCK, BLOCK;	
	}

	//public static PlainVisitor v;
	public static Document doc = new Document();  //Document Object
	public static boolean startB = false;		 
	public static boolean endB = false;
	public static ArrayList<String> stringList = new ArrayList<>();
	public static boolean nl_flag = false; // new line count 
	
	
	
	// temporary variables
	public static String nodeString = "";
	public static String curLine = "";
	public static String nextLine = "";
	public static String prevLine = "";
	public static NodeType ntype = NodeType.BLOCK; // default.
	
	// Node Sytle attributes
	public static HeaderNode.NodeStyle hStyle = null;
/*	public static ListNode.ListNode nstyle;
	public static HeaderNode.NodeStyle nstyle;
	public static HeaderNode.NodeStyle nstyle;
*/
	
	
	// testing
	static String path = "C:" + File.separator + "Users" + File.separator + "Eunbee" + File.separator + "workspace" + File.separator + "file.txt";
	static File f = new File(path);
	
	
	
// CONSTRUCTOR
	public MDParser() {
		// TODO Auto-generated constructor stub
	}
	public MDParser(File file)
	{
		parser2(file);
	}
// OPERATIONS 
	

	public static void comparePN(String line)
	{

		nextLine = line;
		
		// check if nextLine starts the node.
		if(isStart(nextLine))
		{
			startB = true;
		}
		// check if nextLine ends the node.	
		if(isEnd(nextLine))
		{
			endB = true;
			return;		
		}
		else
		{
			
		}

		// update variables.
		if(!(prevLine.trim().isEmpty()))
			nodeString = nodeString + prevLine + "\n";
		prevLine = nextLine;

		
	}

	
	public static boolean isStart(String line)
	{		
		if(line.trim().isEmpty())
			return false;
   	    // HEADERS
		else if(line.startsWith("# ")||line.startsWith("## ")||line.startsWith("### ")
					||line.startsWith("#### ")||line.startsWith("##### ")||line.startsWith("###### "))
		{
			// create a node with buffered string previous to this line.
			if(!(nodeString.trim().isEmpty()))
			{
				if(!(prevLine.trim().isEmpty()))
					nodeString = nodeString + prevLine + "\n";
				createNode(nodeString);
				nodeString = "";
				prevLine = "";
			}		
			// Setting
			ntype = NodeType.HEADER;
			return true;
		}
		
		// UNORDERED LIST - START
		else if(line.startsWith("* ")&& ntype != NodeType.UL_ITEM)
		{			
			System.out.println("999999");
			// create a node with prev lines (stored @nodeString)
			//System.out.println("ns: " + nodeString + ", prev: " + prevLine);
			if(!(prevLine.trim().isEmpty())&&ntype != NodeType.HEADER)
			{
				nodeString = prevLine + "\n";
				createNode(nodeString);
				nodeString = "";
				prevLine = "";
			}
			ntype = NodeType.UL_ITEM;
			return true;
		}
		// ORDERED LIST - START
		else if(Character.isDigit(line.charAt(0))&&ntype != NodeType.OL_ITEM)
		{
			System.out.println("12345676");
			if((line.substring(1,line.length())).startsWith(". ")) // ordered list
			{
				System.out.println("666666");
				if(!(prevLine.trim().isEmpty())&&ntype != NodeType.HEADER)
				{
					System.out.println("877777");		
					nodeString = nodeString + prevLine + "\n";
					createNode(nodeString);
					nodeString = "";
					prevLine = "";
				}
				ntype = NodeType.OL_ITEM;
				return true;
			}
			else
				return false;
		}
		
		// QUOTED_BLOCK - START
		else if(line.startsWith(">")&& ntype != NodeType.Q_BLOCK)
		{
		// create (prev) node.
			if(!(prevLine.trim().isEmpty())&&ntype != NodeType.HEADER)
			{
				nodeString = prevLine + "\n";
				createNode(nodeString);
				nodeString = "";
				prevLine = "";
			}
			ntype = NodeType.Q_BLOCK;
			return true;
		}
		
		// BLOCK(PLAIN TEXT)
		else if(prevLine=="" && ntype == NodeType.BLOCK)
		{
			return true;
		}
		
			
		
	/*	// ORDER/UNORDER LIST: ���� ù ���� ��츸!
		else if(prevLine == null || nodeString == null)
		{
			if(line.startsWith("* "))	// Unordered lists
			{
				createNode(nodeString, nodeType);	// node ����
				nodeString = nodeString + line; 	// nodeString update
				nodeType = Node.NodeType.UNORDER;						// nodeType ����
			}
			else if(line.startsWith("1.")||line.startsWith("2.")||line.startsWith("3.")	// Ordered Lists
					||line.startsWith("4.")||line.startsWith("5.")||line.startsWith("6.")
					||line.startsWith("7.")||line.startsWith("8.")||line.startsWith("9."))
			{
				nodeString = nodeString + line; 	// nodeString update
				nodeType = Node.NodeType.ORDER;								// nodeType ����
			}
			else
			{
				nodeString = nodeString + line; 	// nodeString update
			}
			return true;
		}
		
		*/
		else{
			return false;
			
		}

	}
	
	
	public static boolean isEnd(String line)
	{
		int h1 = 0, h2 = 0;
	//	boolean sym = false;
		
		if((!line.trim().isEmpty()) && line.charAt(0)== '=')
		{
			for(h1 =0; h1 < line.length(); h1++)
			{
				if(line.charAt(h1) != '=')
				{
					break;
				}
			}			
			if(h1 == line.length())
			{
				if(!(nodeString.trim().isEmpty()))
				{
					createNode(nodeString);
				}
				// create (new) node.
				ntype = NodeType.HEADER;
				hStyle = HeaderNode.NodeStyle.values()[0];
				createNode(prevLine, HeaderNode.NodeStyle.H1);
				initializeAll();
			}
			
			return true;
		}
		else if((!line.trim().isEmpty()) && line.charAt(0)== '-')
		{
			for(h2 = 0; h2 < line.length(); h2++)
			{
				if(line.charAt(h2) != '-')
				{
					break;
				}
			}	
			
			if(h2 == line.length())
			{
				// create (prev) node.
				if(!(nodeString.trim().isEmpty()))
				{
					createNode(nodeString);
				}
				// create (new) node.
				ntype = NodeType.HEADER;
				hStyle = HeaderNode.NodeStyle.values()[1];
				createNode(prevLine, HeaderNode.NodeStyle.H2);
				initializeAll();
			}
			
			return true;
		}
		

		else if(ntype == NodeType.HEADER && line.startsWith("#"))
		{
		

		// string processing
			// eliminate '#' in the front
			int hnum = HeaderNum(line, '#');
			hStyle = HeaderNode.NodeStyle.values()[hnum - 1];
			line = line.substring(hnum + 1);
			// eliminate '#' at the end
			while(line.endsWith("#"))		
			{
				line = line.substring(0, line.length()-1);
			}
			
			// node ����
			createNode(line, hStyle);
			initializeAll();
			return true;
		}
		else if(ntype==NodeType.UL_ITEM || ntype == NodeType.OL_ITEM || ntype==NodeType.Q_BLOCK)
		{
			System.out.println("3");
			if(line.trim().isEmpty())//blank line
			{
				if(!nl_flag)
				{
					nl_flag = true;	
					return false;
				}
				else
				{
					// two or blank lines! 
					// Create unordered item_list Node.
					nodeString = nodeString + line;
					createNode(nodeString);
					initializeAll();
					return true;
				}
			}
			else
			{
				nl_flag = false;
			}
			return false;
		}
		
		else
		{
			return false;
		}
	}
	
	
	public static int HeaderNum(String s, char c) // # �������� method
	  {
	    int counter = 0;
	    boolean foundOne = false;
	    for( int i = 0; i < s.length(); i++)
	    {
	      if( s.charAt(i) == c )
	      {
	        counter += 1;
	        foundOne = true;
	      }
	      else {
	        if(foundOne) break;
	      }
	    }
	    return counter;
	  }
	
	

// CREATE NODE methods :
	public static void createNode(String ns)
	{
		//create Node and set its type.
		Node node;
		if(ntype == NodeType.BLOCK)
		{
			node = new BlockNode(ns);
			doc.nodes.add(node);
		}
		else if(ntype == NodeType.UL_ITEM || ntype == NodeType.OL_ITEM)  
		{
			if(ntype==NodeType.UL_ITEM){
				node = new ItemListNode(ns,ItemListNode.NodeStyle.Unordered);
			}else{
				node = new ItemListNode(ns,ItemListNode.NodeStyle.Ordered);
			}
				
			doc.nodes.add(node);
		}
		else if(ntype == NodeType.Q_BLOCK)  
		{
			node = new QuotedBlockNode(ns);
			doc.nodes.add(node);
		}
		
		
		System.out.println("\nnode 생성:" + ns + "type: " + ntype);
		
	}
	
	// header node
	public static void createNode(String s, HeaderNode.NodeStyle hs)
	{
		//create Node and set its type.
		HeaderNode node;
		node = new HeaderNode(s, hs);
		doc.nodes.add(node);
		System.out.println("node 생성:" + s + "\ntype " + ntype);
		
	}

	
	
	// Flush method. Empty all temps.
	public static void initializeAll()
	{
		startB = false;
		endB = false;
		nodeString = "";
		curLine = "";
		nextLine = "";
		prevLine = "";
		ntype = NodeType.BLOCK;
		hStyle = null;
	}
	public void parser2(File Inputfile){
		String bufferLine = "";
		String innerBuffer = "";
		try {
			FileReader fileReader = new FileReader(Inputfile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			NodeType type = NodeType.PLAIN;

			while((bufferLine = bufferedReader.readLine()) != null){
				Node node;
				Pattern headerP1 = Pattern.compile("^[#]+");			// define header pattern
				Pattern headerP2 = Pattern.compile("^[=]+[ ]?$");
				Pattern headerP3 = Pattern.compile("^[-]+[ ]?$");
				Matcher headerM1 = headerP1.matcher(bufferLine);
				Matcher headerM2 = headerP2.matcher(bufferLine);
				Matcher headerM3 = headerP3.matcher(bufferLine);
				
				Pattern quotedP1 = Pattern.compile("^[>]{1}");			// define quoted block pattern
				Matcher quotedM1 = quotedP1.matcher(bufferLine);
				
				Pattern ulP1 = Pattern.compile("^[ ]?[+]{1}");
				Pattern ulP2 = Pattern.compile("^[ ]?[-]{1}");
				Pattern ulP3 = Pattern.compile("^[ ]?[*]{1}");
				Matcher ulM1 = ulP1.matcher(bufferLine);
				Matcher ulM2 = ulP2.matcher(bufferLine);
				Matcher ulM3 = ulP3.matcher(bufferLine);
				
				Pattern olP = Pattern.compile("^[ ]?[0-9]+[.]{1}");
				Matcher olM = olP.matcher(bufferLine);
				
				
				
				if(headerM1.find()){						// when header identifier is found
					int n = 0;
					while(bufferLine.startsWith("#")){		// delete '#'
						bufferLine = bufferLine.substring(1);
						n++;
					}
					while(bufferLine.startsWith(" ")){		// delete white space before the character
						bufferLine = bufferLine.substring(1);
					}
					node = new HeaderNode(bufferLine,n);	// make headerNode with edited string
					doc.nodes.add(node);					// push a node into document
					if(!(innerBuffer=="")){					// buffer control 
						createNode2(innerBuffer,type);	
						innerBuffer="";
					}
				}else if(headerM2.find()){					//when header identifier is found(========)
					node = new HeaderNode(innerBuffer,1);
					doc.nodes.add(node);
					innerBuffer="";
				}else if(headerM3.find()){
					node = new HeaderNode(innerBuffer,2);
					doc.nodes.add(node);
					innerBuffer="";
				}else if(quotedM1.find()){
					if(type!=NodeType.Q_BLOCK){
						if(!(innerBuffer=="")){					// buffer control 
							createNode2(innerBuffer,type);	
							innerBuffer="";
						}
						type = NodeType.Q_BLOCK;
					}
					while(bufferLine.startsWith(">"))bufferLine = bufferLine.substring(1);
					while(bufferLine.startsWith(" "))bufferLine = bufferLine.substring(1);
					innerBuffer = innerBuffer + bufferLine + "<br>\n";
				}else if(ulM1.find()){
					if(type!=NodeType.UL_ITEM){					//start of node
						if(!(innerBuffer=="")){					// buffer control 
							createNode2(innerBuffer,type);	
							innerBuffer="";
						}
						type = NodeType.UL_ITEM;
					}
					while(bufferLine.startsWith(" "))bufferLine = bufferLine.substring(1);
					innerBuffer = innerBuffer + bufferLine + "\n";
				}else if(ulM2.find()){
					if(type!=NodeType.UL_ITEM){					//start of node
						if(!(innerBuffer=="")){					// buffer control 
							createNode2(innerBuffer,type);	
							innerBuffer="";
						}
						type = NodeType.UL_ITEM;
					}
					while(bufferLine.startsWith(" "))bufferLine = bufferLine.substring(1);
					innerBuffer = innerBuffer + bufferLine + "\n";
				}else if(ulM3.find()){
					if(type!=NodeType.UL_ITEM){					//start of node
						if(!(innerBuffer=="")){					// buffer control 
							createNode2(innerBuffer,type);	
							innerBuffer="";
						}
						type = NodeType.UL_ITEM;
					}
					while(bufferLine.startsWith(" "))bufferLine = bufferLine.substring(1);
					innerBuffer = innerBuffer + bufferLine + "\n";
				}else if(olM.find()){
					if(type!=NodeType.OL_ITEM){					//start of node
						if(!(innerBuffer=="")){					// buffer control 
							createNode2(innerBuffer,type);	
							innerBuffer="";
						}
						type = NodeType.OL_ITEM;
					}
					while(bufferLine.startsWith(" "))bufferLine = bufferLine.substring(1);
					innerBuffer = innerBuffer + bufferLine + "\n";
				}else if(bufferLine.isEmpty()){
					if(!(innerBuffer=="")){					// buffer control 
						createNode2(innerBuffer,type);	
						innerBuffer="";
					}
				}else{
					if(type!=NodeType.BLOCK){
						if(!(innerBuffer=="")){					// buffer control 
							createNode2(innerBuffer,type);	
							innerBuffer="";
						}
						type = NodeType.BLOCK;
					}
					innerBuffer = innerBuffer + bufferLine + "<br>\n";
				}
			}
			if(!(innerBuffer=="")){					// buffer control 
				createNode2(innerBuffer,type);	
				innerBuffer="";
			}
			bufferedReader.close();
		} catch(IOException ex) {
			System.out.println("Error reading file '" + Inputfile ); 
		}
		
	}
	private void createNode2(String innerBuffer, NodeType type) {
		Node node;
		switch(type){
		case BLOCK:
			node = new BlockNode(innerBuffer);
			doc.nodes.add(node);
			break;
		case HEADER:
			break;
		case OL_ITEM:
			node = new ItemListNode(innerBuffer,ItemListNode.NodeStyle.Ordered);
			doc.nodes.add(node);
			System.out.println("node created : " + type);
			break;
		case PLAIN:
			break;
		case Q_BLOCK:
			node = new QuotedBlockNode(innerBuffer);
			doc.nodes.add(node);
			break;
		case UL_ITEM:
			node = new ItemListNode(innerBuffer,ItemListNode.NodeStyle.Unordered);
			doc.nodes.add(node);
			System.out.println("node created : " + type);
			break;
		default:
			break;
		
		}
		
	}
	public void parser(File Inputfile) {
		
		String bufferLine = "";
		// ���� �� �پ� �о stringList array�� ����
		try {
			FileReader fileReader = new FileReader(Inputfile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int count = 0;
			while((bufferLine = bufferedReader.readLine()) != null) 
			{
				stringList.add(bufferLine);
				count++;
			}
			bufferedReader.close();
		} catch(IOException ex) {
			System.out.println("Error reading file '" + Inputfile ); 
		}
		
		// String array �� string ����  parse ����
		for(int i = 0; i <stringList.size();i++)
		{
			comparePN(stringList.get(i));
		}
		//callVisitor();
	}
	/*
	public static void callVisitor(){
		Iterator<Node> it = doc.nodes.iterator();
		while(it.hasNext()){
			it.next().accept(v);
		}
	}
	
	public Visitor getVisitor(){
		return v;
	}
	public String getHTML(){
		return v.getDocument();
	}
	*/
	public Document getDoc(){
		return doc;
	}
	/*
	public void fileWrite(String str){
		try {
		      ////////////////////////////////////////////////////////////////
		      BufferedWriter out = new BufferedWriter(new FileWriter("asdf.html"));
		      out.write(str);
		      out.close();
		      ////////////////////////////////////////////////////////////////
		    } catch (IOException e) {
		        System.err.println(e); // 에러가 있다면 메시지 출력
		        System.exit(1);
		    }
	}
	*/
	public static void main(String args[])
	{
		//testing
		MDParser mdp = new MDParser(f);
		
			
		
	}
}
