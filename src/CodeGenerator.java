
public class CodeGenerator {
	public CodeGenerator(){
		
	}
	
	public String nodeCodeGenFront(Node node,String str){
		switch(node.getNodeType()){
			default:
				return str;
				
			case HEADER:
				return str + "<h>";

			case LIST:
				break;
			case QBLOCK: 
				break;
			case BLOCK: 
				break;
			case HORIZONTAL:
				break;
			case ITEM_LIST:
				break;		
		}
		return str;
	}
	
	public String nodeCodeGenBack(Node node,String str){
		switch(node.getNodeType()){
			default:
				return str;
				
			case HEADER:
				return str + "</h>";

			case LIST:
				break;
			case QBLOCK: 
				break;
			case BLOCK: 
				break;
			case HORIZONTAL:
				break;
			case ITEM_LIST:
				break;		
		}
		return str;
	}
	
	public String tokenCodeGenFront(Token token,String str){
		switch(token.getTokenType()){
		case HEADER:
			break;
		case HTML:
			break;
		case IMAGE:
			break;
		case ITEM_LIST:
			break;
		case LINK:
			break;
		case PLAIN:
			break;
		case Q_BLOCK:
			break;
		case STYLE:
			break;
		default:
			break;
		}
		return str;
	}
	
	public String tokenCodeGenBack(Token token,String str){
		switch(token.getTokenType()){
		case HEADER:
			break;
		case HTML:
			break;
		case IMAGE:
			break;
		case ITEM_LIST:
			break;
		case LINK:
			break;
		case PLAIN:
			break;
		case Q_BLOCK:
			break;
		case STYLE:
			break;
		default:
			break;
		}
		return str;
	}

}
