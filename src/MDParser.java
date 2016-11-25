import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MDParser{
	
// ATTRIBTUES

	public static enum NodeType
	{
		PLAIN, HEADER, Q_BLOCK, LIST, BLOCK;	
	}

	
	static Document doc = new Document();  //Document Object
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
	static String path = "C:" + File.separator + "Users" + File.separator + "Eunbee" + File.separator + "workspace" + File.separator + "file.txt";
	static File f = new File(path);
	
	
// CONSTRUCTOR
	public MDParser(File file)
	{
		parser(file);
	}
// OPERATIONS 
	
	public static void comparePN(String line)
	{
		// update.
		nodeString = nodeString + prevLine + "\n";
		nextLine = line;
		
		//System.out.println("p: "+ prevLine + ", n: " + nextLine);
		
		// check if nextLine starts the node.

		if(isStart(nextLine))
		{
			startB = true;
			prevLine = nextLine;
			// action: 현재 nodeString으로 new node 생성하고  NodeArr에 저장, nextLine은 비워진 nodeString에 저장
		}	
		// check if nextLine ends the node.	
		if(isEnd(nextLine))
		{
			endB = true;
			return;		// action: 현재 nodeString에 자신을 더하고  그 nodeString으로 new node 생성, NodeArr에 추가.
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
		
		
	}

	
	public static boolean isStart(String line)
	{		
		
   	    // HEADERS : #으로 시작하는 header들
		if(line.startsWith("# ")||line.startsWith("## ")||line.startsWith("### ")
					||line.startsWith("#### ")||line.startsWith("##### ")||line.startsWith("###### "))
		{
			// create a node with buffered string previous to this line.
			if(!(nodeString.trim().isEmpty()))
			{
				createNode(nodeString, ntype);
			}
			else
			{
				c
			}
			
			
			
			// Node 생성(이전까지의  NodeString으로 ) 
			createNode(nodeString, ntype);
			
			// Setting
			ntype = NodeType.HEADER;			
			System.out.println("start: header");
			return true;
		}
		// Plain text
		else if(prevLine=="" && ntype == NodeType.BLOCK)
		{
			nodeString = line + "\n";
	

			System.out.println("start: plain - " + nodeString);
			return true;
		}
		
			
		
	/*	// ORDER/UNORDER LIST: 제일 첫 줄일 경우만!
		else if(prevLine == null || nodeString == null)
		{
			if(line.startsWith("* "))	// Unordered lists
			{
				createNode(nodeString, nodeType);	// node 생성
				nodeString = nodeString + line; 	// nodeString update
				nodeType = Node.NodeType.UNORDER;						// nodeType 설절
			}
			else if(line.startsWith("1.")||line.startsWith("2.")||line.startsWith("3.")	// Ordered Lists
					||line.startsWith("4.")||line.startsWith("5.")||line.startsWith("6.")
					||line.startsWith("7.")||line.startsWith("8.")||line.startsWith("9."))
			{
				createNode(nodeString, nodeType);	// node 생성
				nodeString = nodeString + line; 	// nodeString update
				nodeType = Node.NodeType.ORDER;								// nodeType 설절
			}
			else
			{
				nodeString = nodeString + line; 	// nodeString update
			}
			return true;
		}
		
		*/
		else
			return false;

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
				System.out.println("end: h1");
				hStyle = HeaderNode.NodeStyle.values()[0];
			}
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
				System.out.println("end: h2");
				hStyle = HeaderNode.NodeStyle.values()[1];
			}
		}
	
		if(hStyle != null)
		{
			// Node 생성		
			nodeString = prevLine +"\n"; 	// nodeString update	
			createNode(nodeString, NodeType.HEADER);
		
			return true;
		}
		else
		{
			System.out.println("not end -" + nodeString + "string끝");
			return false;

		}

	}
	
	
	public static int HeaderNum( String s, HeaderNode.NodeStyle ns ) // # 개수세는 method
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
		initializeAll();
	}
	
	// header node
	public static void createNode(String ns, HeaderNode.NodeStyle hStyle)
	{
		//create Node and set its type.
		Node node;
		node = new HeaderNode(ns, hStyle);
		doc.nodes.add(node);
		
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
		// 파일 한 줄씩 읽어서 stringList array에 저장
		try {
			FileReader fileReader = new FileReader(Inputfile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int count = 0;
			while((bufferLine = bufferedReader.readLine()) != null) 
			{
				stringList.add(bufferLine);
				//System.out.println("line: " + bufferLine );
				count++;
			} 
			bufferedReader.close();
		} catch(IOException ex) {
			System.out.println("Error reading file '" + Inputfile ); 
		}
		
		
		// String array 각 string 별로  parse 시작
		for(int i = 0; i <stringList.size();i++)
		{
			comparePN(stringList.get(i));
			
		}	
	}
	
	public static void main(String args[])
	{
		//testing
		MDParser mdp = new MDParser(f);
		
			
		
	}
}
