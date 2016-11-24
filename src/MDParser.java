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
	public static String nodeString = null;
	public static String curLine = null;
	public static String nextLine = null;
	public static String prevLine = null;
	public static NodeType ntype = NodeType.BLOCK; // default.
	
	// Node Sytle attributes
	public static HeaderNode.NodeStyle hStyle;
/*	public static ListNode.ListNode nstyle;
	public static HeaderNode.NodeStyle nstyle;
	public static HeaderNode.NodeStyle nstyle;
*/
	
	// testing
	//static String path = "C:" + File.separator + "Users" + File.separator + "Eunbee" + File.separator + "workspace" + File.separator + "file.txt";
	//static File f = new File(path);
	
	
// CONSTRUCTOR
	public MDParser(File file)
	{
		parser(file);
	}
// OPERATIONS 
	
	public static void comparePN(String line)
	{
		// update.
		prevLine = nextLine;	
		nextLine = line;
		
		System.out.println("\nprev: " + prevLine + "\n next: " + nextLine);
		
		// check if nextLine starts the node.
		if(isStart(nextLine))
		{
			System.out.println("This is a start\n");
			return;		// action: 현재 nodeString으로 new node 생성하고  NodeArr에 저장, nextLine은 비워진 nodeString에 저장
		}


			
		// check if nextLine ends the node.	
		if(isEnd(nextLine))
		{
			System.out.println("This is an end\n");
			return;		// action: 현재 nodeString에 자신을 더하고  그 nodeString으로 new node 생성, NodeArr에 추가.
		}
	
		
	

	// RULES!!!!!!  나중에 구현합니다아.... 	
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
		
			// Node 생성(이전까지의  NodeString으로 ) 		
			createNode(nodeString, ntype);
			
	
			// String 가공 완료 (header syntax 떼어냄)
			ntype = NodeType.HEADER;
			int hnum = HeaderNum(line, '#');			
			hStyle = HeaderNode.NodeStyle.values()[hnum - 1]; 
			nodeString = line.substring(hnum + 1) + "\n"; 	// nodeString update
			
			
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
		int count_1 = 0; //counts '='
		int count_2 = 0; //counts '-'

		for(int i =0; i < line.length(); i++)
		{
			if(line.charAt(i) == '=')
			{
				count_1++;
			}
		}
		
		for(int i =0; i < line.length(); i++)
		{
			if(line.charAt(i) == '-')
			{
				count_2++;
			}
		}
		if(count_1 == line.length() || count_2 == line.length())
		{
			if(count_1 == line.length()) // '='
			{
				hStyle = HeaderNode.NodeStyle.values()[0];
				
			}
			else if(count_2 == line.length())
			{
				hStyle = HeaderNode.NodeStyle.values()[1];
			}
			
			
			// Node 생성		
			nodeString = prevLine +"\n"; 	// nodeString update				
			createNode(nodeString, NodeType.HEADER);
		
			return true;
		}
		else if(hStyle != null)
		{
			System.out.println("this end");
		}
			return false;
	}
	
	
	
	public static int HeaderNum( String s, char c ) // # 개수세는 method
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
	
	
	
	// new node 생성, array에 추가
	public static void createNode(String ns, NodeType type)
	{
		//create Node and set its type.
		Node node;
		if(type.ordinal() == 1) // header
		{
			node = new HeaderNode(ns, hStyle);
			doc.nodes.add(node);
		}
		else
		{
		//	node = new BlockNode(ns);
		}
		
		System.out.println("node :" + ns);
		
		
		//initialize all temp variables.
		initializeAll();
	}
	
	// Flush method. Empty all temps.
	public static void initializeAll()
	{
		startB = false;
		endB = false;
		nodeString = null;
		curLine = null;
		nextLine = null;
		prevLine = null;
		ntype = NodeType.BLOCK;
		hStyle = null;
	}

	
	public void parser(File Inputfile) {
		
		String line = null;
		// 파일 한 줄씩 읽어서 stringList array에 저장
		try {
			FileReader fileReader = new FileReader(Inputfile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int count = 0;
			while((line = bufferedReader.readLine()) != null) 
			{
				stringList.add(line);
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
	
	/*
	public static void main(String args[])
	{
		//testing
		MDParser mdp = new MDParser(f);
		
			
		
	}
	*/
}
