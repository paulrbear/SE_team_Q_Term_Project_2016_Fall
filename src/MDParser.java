import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;;

public class MDParser{
	
// ATTRIBTUES

	public static enum NodeType
	{
		PLAIN, HEADER, Q_BLOCK, LIST, BLOCK;	
	}

	public static PlainVisitor v;
	public static Document doc = new Document();  //Document Object
	public static boolean startB = false;		 
	public static boolean endB = false;
	
	public static ArrayList<String> stringList = new ArrayList<>();
	
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
//	static String path;
//	static File f = new File(path);
	
	
// CONSTRUCTOR
	public MDParser() {
		// TODO Auto-generated constructor stub
	}
	public MDParser(File file)
	{
		parser(file);
	}
// OPERATIONS 
	

	public static void comparePN(String line)
	{

		nextLine = line;
		
		// check if nextLine starts the node.
		if(isStart(nextLine))
		{
			startB = true;
				
			// action: ���� nodeString���� new node �����ϰ�  NodeArr�� ����, nextLine�� ����� nodeString�� ����
		}
		System.out.println("1");
		// check if nextLine ends the node.	
		if(isEnd(nextLine))
		{
			endB = true;
			return;		// action: ���� nodeString�� �ڽ��� ���ϰ�  �� nodeString���� new node ����, NodeArr�� �߰�.
		}
		else
		{
			prevLine = nextLine;
		}
			// RULES 	
		// blank line
	//	if(line.trim().isEmpty())
		//{
	//		nodeString
	//	}
		
		// update temps.
		nodeString = nodeString + prevLine + "\n";
		
	}

	
	public static boolean isStart(String line)
	{		
		
   	    // HEADERS : #���� �����ϴ� header��
		if(line.startsWith("# ")||line.startsWith("## ")||line.startsWith("### ")
					||line.startsWith("#### ")||line.startsWith("##### ")||line.startsWith("###### "))
		{
			// create a node with buffered string previous to this line.
	
			
			if(!(nodeString.trim().isEmpty()))
			{
				createNode(nodeString);
			}			

			// Setting
			ntype = NodeType.HEADER;
			
			return true;
		}
		// Plain text
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
				hStyle = HeaderNode.NodeStyle.values()[0];

				// Node ����		
				nodeString = prevLine +"\n"; 	// nodeString update	
				createNode(nodeString, HeaderNode.NodeStyle.H1);
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
				hStyle = HeaderNode.NodeStyle.values()[1];
				
				// Node ����		
				nodeString = prevLine +"\n"; 	// nodeString update	
				createNode(nodeString, HeaderNode.NodeStyle.H2);
			}
			
			return true;
		}
		else if(ntype == NodeType.HEADER && line.startsWith("#"))
		{
			// string ����
			int hnum = HeaderNum(line, '#');
			hStyle = HeaderNode.NodeStyle.values()[hnum - 1];
		
			line = line.substring(hnum + 1);
		
			// node ����
			createNode(line, hStyle);
			
			return true;
		}
		else		
			return false;
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
	public static void createNode(String s)
	{
		//create Node and set its type.
	/*	Node node;
		node = new PlainNode(ns);
			doc.nodes.add(node);
	*/
			//initialize all temp variables.
		
		System.out.println("��� ����:\n" + s);
	//	initializeAll();
	}
	
	// header node
	public static void createNode(String s, HeaderNode.NodeStyle hs)
	{
		//create Node and set its type.
		HeaderNode node;
		node = new HeaderNode(s, hs);
		doc.nodes.add(node);
		System.out.println("��� ����:\n" + s);
		
		//initialize all temp variables.
		initializeAll();
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
				System.out.println(bufferLine);
				count++;
			} 
			bufferedReader.close();
		} catch(IOException ex) {
			System.out.println("Error reading file '" + Inputfile ); 
		}
		
		System.out.println("before comparePN");
		// String array �� string ����  parse ����
		for(int i = 0; i <stringList.size();i++)
		{
			comparePN(stringList.get(i));
		}
		System.out.println("after comparePN");
		callVisitor();
	}
	
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
	/*
	public static void main(String args[])
	{
		//testing
		
		
	}
	*/
}
