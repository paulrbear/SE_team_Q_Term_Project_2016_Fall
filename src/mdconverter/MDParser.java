package mdconverter;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MDParser{
	
// ATTRIBTUES

	public static enum NodeType
	{
		PLAIN, HEADER, UL_ITEM, OL_ITEM, Q_BLOCK, BLOCK, HTMLTag, EMPTY_LINE, CODEBLOCK, HORIZON;	
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
	
	
	
	
	
// CONSTRUCTOR

	public MDParser(File file)
	{
		parser2(file);
	}
	
	
// OPERATIONS 
	
	//parser gets file and buffered reader read it by a line and parse it as nodes.  

	private void parser2(File Inputfile){
		String bufferLine = "";
		String innerBuffer = "";
		
		try {
			FileReader fileReader = new FileReader(Inputfile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			NodeType type = NodeType.EMPTY_LINE;			// toggle switch for parsing
			
			
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
				
				Pattern ulP1 = Pattern.compile("^[ ]?[+]{1}[ ]+");			// define unordered list pattern
				Pattern ulP2 = Pattern.compile("^[ ]?[-]{1}[ ]+[^-]+");
				Pattern ulP3 = Pattern.compile("^[ ]?[*]{1}[ ]+[^*]+");
				Matcher ulM1 = ulP1.matcher(bufferLine);
				Matcher ulM2 = ulP2.matcher(bufferLine);
				Matcher ulM3 = ulP3.matcher(bufferLine);
				
				Pattern olP = Pattern.compile("^[ ]?[0-9]+[.]{1}");			// define ordered list pattern 
				Matcher olM = olP.matcher(bufferLine);
				
				Pattern htmlP = Pattern.compile("^[<]{1}.*[>]{1}");			// define html tag pattern
				Matcher htmlM = htmlP.matcher(bufferLine);
				
				Pattern horizonP = Pattern.compile("^[*]+[ ]*[*]+[ ]*[*]+[ ]*$");	//define horizon pattern.
				Pattern horizonP2 = Pattern.compile("^[-]+[ ]*[-]+[ ]*[-]+[ ]*$");
				Matcher horizonM = horizonP.matcher(bufferLine);
				Matcher horizonM2 = horizonP2.matcher(bufferLine);
				
				Pattern codeblockP1 = Pattern.compile("^[ ]{4}");			//define codeblock pattern
				Pattern codeblockP2 = Pattern.compile("^\t");
				Matcher codeblockM1 = codeblockP1.matcher(bufferLine);
				Matcher codeblockM2 = codeblockP2.matcher(bufferLine);
				
				
				bufferLine = backslashEscape(bufferLine); // replace every backslash escapes
				
				// HTML tags
				if(htmlM.find()){
					type = NodeType.HTMLTag;
					innerBuffer = innerBuffer + bufferLine + "\n";
				}
				// when header identifier is found
				else if(headerM1.find()){						
					int n = 0;
					for(int i=0;i<6;i++){
						if(bufferLine.startsWith("#")){		// delete '#'
							bufferLine = bufferLine.substring(1);
							n++;
						}
					}
					bufferLine = bufferLine.trim();
					node = new HeaderNode(bufferLine,n);	// make headerNode with edited string
					doc.nodes.add(node);					// push a node into document
					if(!(innerBuffer=="")){					// buffer control 
						createNode2(innerBuffer,type);	
						innerBuffer="";
					}
				}else if(headerM2.find()){					//when header identifier is found(========)
					if(type!=NodeType.EMPTY_LINE){
						node = new HeaderNode(innerBuffer,1);
						doc.nodes.add(node);
						innerBuffer="";
					}
				}else if(headerM3.find()){
					if(type!=NodeType.EMPTY_LINE){
						node = new HeaderNode(innerBuffer,2);
						doc.nodes.add(node);
						innerBuffer="";
					}else{
						createNode2("<hr />\n",NodeType.PLAIN);
						innerBuffer="";
					}
				}
				
				//quoted block
				else if(quotedM1.find()){
					if(type!=NodeType.Q_BLOCK){
						if(!(innerBuffer=="")){					// buffer control 
							createNode2(innerBuffer,type);	
							innerBuffer="";
						}
						type = NodeType.Q_BLOCK;
					}
					while(bufferLine.startsWith(">"))bufferLine = bufferLine.substring(1);
					while(bufferLine.startsWith(" "))bufferLine = bufferLine.substring(1);
					innerBuffer = innerBuffer + bufferLine + " \n";
				}
				
				//horizon 
				else if(horizonM.find() || horizonM2.find()){
					System.out.println("hr" + bufferLine);
					if(!(innerBuffer=="")){					// buffer control 
						createNode2(innerBuffer,type);	
						innerBuffer="";
					}
					
					createNode2("<hr />\n",NodeType.PLAIN);
					innerBuffer="";
				}
				
				//unordered list
				else if(ulM1.find()){
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
				}
				
				// ordered list
				else if(olM.find()){
					if(type!=NodeType.OL_ITEM){					//start of node
						if(!(innerBuffer=="")){					// buffer control 
							createNode2(innerBuffer,type);	
							innerBuffer="";
						}
						type = NodeType.OL_ITEM;
					}
					while(bufferLine.startsWith(" "))bufferLine = bufferLine.substring(1);
					innerBuffer = innerBuffer + bufferLine + "\n";
				}
				// blank line
				else if(bufferLine.trim().isEmpty()){
					if(!(innerBuffer=="")){					// buffer control 
						createNode2(innerBuffer,type);	
						innerBuffer="";
					}
					type = NodeType.EMPTY_LINE;
				}
				// code block
				else if(codeblockM1.find()){
					if(type==NodeType.UL_ITEM || type==NodeType.OL_ITEM){
						if(bufferLine.trim().startsWith("*")||bufferLine.trim().startsWith("+")||bufferLine.trim().startsWith("-")||bufferLine.trim().startsWith("[0-9][.]"))
							innerBuffer = innerBuffer + bufferLine + "\n";
					}else{
						if(type!= NodeType.CODEBLOCK){
							if(!(innerBuffer=="")){					// buffer control 
								createNode2(innerBuffer,type);	
								innerBuffer="";
							}
							type = NodeType.CODEBLOCK;
						}
						innerBuffer = innerBuffer + bufferLine.trim() + "\n";
					}
				}else if(codeblockM2.find()){
					if(type==NodeType.UL_ITEM){
						if(bufferLine.trim().startsWith("*")||bufferLine.trim().startsWith("+")||bufferLine.trim().startsWith("-"));
							innerBuffer = innerBuffer + bufferLine + "\n";
					}else{
						if(type!= NodeType.CODEBLOCK){
							if(!(innerBuffer=="")){					// buffer control 
								createNode2(innerBuffer,type);	
								innerBuffer="";
							}
							type = NodeType.CODEBLOCK;
						}
						System.out.println("code block : " + bufferLine);
						innerBuffer = innerBuffer + bufferLine.trim() + "\n";
					}
				}
				
				//no identifier(plain)
				else{
					System.out.println("no node identifier found :" + bufferLine);
					if(type!=NodeType.BLOCK && type!=NodeType.HTMLTag){
						if(!(innerBuffer=="")){					// buffer control 
							createNode2(innerBuffer,type);	
							innerBuffer="";
						}
						type = NodeType.BLOCK;
					}
					innerBuffer = innerBuffer + bufferLine + " \n";
				}
			}
			
			// flush buffer
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
		case CODEBLOCK:
			node = new CodeBlockNode(innerBuffer);
			doc.nodes.add(node);
			break;
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
			node = new PlainToken(innerBuffer);
			doc.nodes.add(node);
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
		case HTMLTag:
			node = new PlainToken(innerBuffer);
			doc.nodes.add(node);
			System.out.println("node created : " + type);
			break;
		default:
			break;
		
		}
		
	}
	private String backslashEscape(String checkString){
		//Backslash			
			/*
			/////////////////////////////////////////

			Markdown provides backslash escapes for the following characters:
			\ backslash
			�� backtick
			* asterisk
			_ underscore
			{} curly braces
			[] square brackets
			() parentheses
			# hash mark
			. dot
			! exclamation mark
			
			////////////////////////////////////////
			*/
		checkString = checkString.replaceAll("\\\\\\*","*");
		checkString = checkString.replaceAll("\\\\\\\\","\\\\");
		checkString = checkString.replaceAll("\\\\[']","\'");
		checkString = checkString.replaceAll("\\\\[_]","_");
		checkString = checkString.replaceAll("\\\\\\{","{");
		checkString = checkString.replaceAll("\\\\\\}","}");
		checkString = checkString.replaceAll("\\\\\\[","[");
		checkString = checkString.replaceAll("\\\\\\}","]");
		checkString = checkString.replaceAll("\\\\\\(","(");
		checkString = checkString.replaceAll("\\\\\\)",")");
		checkString = checkString.replaceAll("\\\\[#]","#");
		checkString = checkString.replaceAll("\\\\[.]",".");
		checkString = checkString.replaceAll("\\\\[!]","!");
		
		checkString = checkString.replaceAll("\\\\[<]", "&lt;");
		checkString = checkString.replaceAll("\\\\[&]", "&amp;");
		
		//return result
		return checkString;
	}

	public Document getDoc(){
		return doc;
	}
}

