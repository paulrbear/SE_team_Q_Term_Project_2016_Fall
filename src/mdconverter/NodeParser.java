package mdconverter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.IOException;



public class NodeParser{

	public enum TokenType{
		PLAIN, EM, STRONG, HTML, LINK, IMAGE, ORDERED_LIST, UNORDERED_LIST, HEADER, Q_BLOCK, LISTED_ITEM, LIST_NESTED_UNORDERED, LIST_NESTED_ORDERED;	
	}
	
	
	
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
		parser(ns,np);
	}
	
	
	public static void parser(String str,Node np){

		StringReader sr = new StringReader(str);
		str.replace("\n","<br>");
		BufferedReader br = new BufferedReader(sr);
		String bufferstr="";
		String buffer2="";
		TokenType toggle=TokenType.PLAIN;
		try {
			while((bufferstr=br.readLine()) != null ){
				// pattern & matcher define by regular expression
				Pattern ulP1 = Pattern.compile("^[ ]?[+]{1}[ ]");  // unordered list
				Pattern ulP2 = Pattern.compile("^[ ]?[-]{1}[ ]"); 	
				Pattern ulP3 = Pattern.compile("^[ ]?[*]{1}[ ]");
				Matcher ulM1 = ulP1.matcher(bufferstr);
				Matcher ulM2 = ulP2.matcher(bufferstr);
				Matcher ulM3 = ulP3.matcher(bufferstr);
				
				Pattern olP = Pattern.compile("^[ ]?[0-9][.]{1}"); //ordered list
				Matcher olM = olP.matcher(bufferstr);
				
				Pattern qbP = Pattern.compile("^[ ]?[>]{1}");	// quoted block
				Matcher qbM = qbP.matcher(bufferstr); 
				
				Pattern strongP = Pattern.compile("[*]{2}[^*]+[*]{2}");	//strong type
				Pattern strongP2 = Pattern.compile("[_]{2}[^_]+[_]{2}");
				Matcher strongM = strongP.matcher(bufferstr);
				Matcher strongM2 = strongP2.matcher(bufferstr);
				
				Pattern emP = Pattern.compile("[^*]?[*]{1}[^*]+[*]{1}[^*]?");	//emphasis type
				Pattern emP2 = Pattern.compile("[^_]?[_]{1}[^_]+[_]{1}[^_]?");
				Matcher emM = emP.matcher(bufferstr);
				Matcher emM2 = emP2.matcher(bufferstr);
				
				Pattern nestedULP1 = Pattern.compile("\t[+]{1}");			//nested list(unordered)
				Pattern nestedULP2 = Pattern.compile("\t[-]{1}");
				Pattern nestedULP3 = Pattern.compile("\t[*]{1}");
				Matcher nestedULM1 = nestedULP1.matcher(bufferstr);
				Matcher nestedULM2 = nestedULP2.matcher(bufferstr);
				Matcher nestedULM3 = nestedULP3.matcher(bufferstr);
				
				Pattern nestedOLP1 = Pattern.compile("\t[0-9][.]");			////nested list(ordered)
				Matcher nestedOLM1 = nestedOLP1.matcher(bufferstr);
				
				
				// if pattern matches with buffered strings
				// parse it recursively until it meets plain token(pure text)
				// when toggle changes, flush the buffer2 to make node
				
				if(ulM1.find()){		// unordered list
					String[] str2;
					str2 = bufferstr.split("^[ ]?[+]{1}",2);
					if(!(buffer2=="")){
						createToken(np,buffer2,toggle);
						buffer2="";
					}
					toggle = TokenType.LISTED_ITEM;
					createToken(np,str2[1].trim(),toggle);
				}else if(ulM2.find()){
					String[] str2;
					str2 = bufferstr.split("^[ ]?[-]{1}",2);
					if(!(buffer2=="")){
						createToken(np,buffer2,toggle);
						buffer2="";
					}
					toggle = TokenType.LISTED_ITEM;
					createToken(np,str2[1].trim(),toggle);
				}else if(ulM3.find()){
					String[] str2;
					str2 = bufferstr.split("^[ ]?[*]{1}",2);
					System.out.println(str2[0]);
					System.out.println(str2[1]);
					if(!(buffer2=="")){
						createToken(np,buffer2,toggle);
						buffer2="";
					}
					toggle = TokenType.LISTED_ITEM;
					createToken(np,str2[1].trim(),toggle);
				}
				else if(olM.find()){					//ordered list
					String[] str2;
					str2 = bufferstr.split("^[ ]?[0-9]+[.]{1}",2);
					System.out.println(str2[0]);
					System.out.println(str2[1]);
					if(!(buffer2=="")){
						createToken(np,buffer2,toggle);
						buffer2="";
					}
					toggle = TokenType.LISTED_ITEM;
					createToken(np,str2[1].trim(),toggle);	
				}
				
				// nested list (unordered)
				else if(nestedULM1.find() || nestedULM2.find() || nestedULM3.find()){	
					System.out.println(toggle);
					if(!(toggle==TokenType.LISTED_ITEM || toggle == TokenType.LIST_NESTED_UNORDERED)){
						if(!(buffer2=="")){
							createToken(np,buffer2,toggle);
							buffer2="";
						}
					}
					buffer2 = buffer2 + bufferstr.trim() + "\n";
					toggle = TokenType.LIST_NESTED_UNORDERED;
				}
				
				// nested list (ordered)
				else if(nestedOLM1.find()){											
					System.out.println(toggle);
					if(!(toggle==TokenType.LISTED_ITEM || toggle == TokenType.LIST_NESTED_ORDERED)){
						if(!(buffer2=="")){
							createToken(np,buffer2,toggle);
							buffer2="";
						}
					}
					buffer2 = buffer2 + bufferstr.trim() + "\n";
					toggle = TokenType.LIST_NESTED_ORDERED;
				}
				
				// quoted block
				else if(qbM.find()){	
					String[] str2;
					str2 = bufferstr.split("^[ ]?[>]{1}", 1);
					if(!(buffer2=="")){
						createToken(np,buffer2,toggle);
						buffer2="";
					}
					toggle = TokenType.Q_BLOCK;
					createToken(np,str2[0],toggle);
				}
				
				// strong token
				else if(strongM.find()){	
					String[] str2;
					str2 = bufferstr.split("[*]{2}",3);
					if(!(buffer2=="")){
						createToken(np,buffer2,toggle);
						buffer2="";
					}
					toggle = TokenType.STRONG;
					parser(str2[0],np);
					createToken(np,str2[1],toggle); 
					parser(str2[2],np); 
				}else if(strongM2.find()){
					String[] str2;
					str2 = bufferstr.split("[_]{2}",3);
					if(!(buffer2=="")){
						createToken(np,buffer2,toggle);
						buffer2="";
					}
					toggle = TokenType.STRONG;
					parser(str2[0],np);
					createToken(np,str2[1],toggle); 
					parser(str2[2],np); 
				}
				
				// em token
				else if(emM.find()){	
					String[] str2;
					str2 = bufferstr.split("[*]{1}",3);
					if(!(buffer2=="")){
						createToken(np,buffer2,toggle);
						buffer2="";
					}
					toggle = TokenType.EM;
					parser(str2[0],np);
					createToken(np,str2[1],toggle); 
					parser(str2[2],np); 
				}else if(emM2.find()){
					String[] str2;
					str2 = bufferstr.split("[_]{1}",3);
					if(!(buffer2=="")){
						createToken(np,buffer2,toggle);
						buffer2="";
					}
					toggle = TokenType.EM;
					parser(str2[0],np);
					createToken(np,str2[1],toggle); 
					parser(str2[2],np); 
				}
				
				// if there is no token identifier -> plain token
				else{				
					createToken(np, bufferstr , TokenType.PLAIN);
				}
				
			}
			if(!(buffer2=="")){
				System.out.println(buffer2);
				createToken(np,buffer2,toggle);
				buffer2="";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//retrieve node, string and tokentype.
	//make new node and put it into document object. 
	
	public static void createToken(Node np, String s, TokenType type)
	{
		Node node;
		switch(type){
		case EM:
			node = new ItalicToken(s);
			np.tokens.add(node);
			break;
		case HEADER:
			break;
		case HTML:
			break;
		case IMAGE:
			break;
		case UNORDERED_LIST:
			node = new ItemListNode(s,ItemListNode.NodeStyle.Unordered);
			np.tokens.add(node);
			break;
		case LISTED_ITEM:
			node = new ListedItem(s);
			np.tokens.add(node);
			break;
		case LIST_NESTED_UNORDERED:
			node = new ItemListNode(s,ItemListNode.NodeStyle.Unordered);
			np.tokens.add(node);
			break;
		case LIST_NESTED_ORDERED:
			node = new ItemListNode(s,ItemListNode.NodeStyle.Ordered);
			np.tokens.add(node);
			break;
		case LINK:
			break;
		case PLAIN:
			node = new PlainToken(s);
			np.tokens.add(node);
			break;
		case Q_BLOCK:
			node = new QuotedBlockNode(s);
			np.tokens.add(node);
			break;
		case STRONG:
			node = new BoldToken(s);
			np.tokens.add(node);
			break;
		default:
			System.out.printf("error in createToken");
			break;
		
		}
	}
/* test code 
	public static void main(String args[]){
		Node node = new Node();
		String str = "+ asdf;lsdkf";
		node.nodeString = str ; 
		node.tokens = new ArrayList<Node>();
		nodeStringParser(node);
	}
*/	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//end of NodeParser Class
