
public class CodeGenerator {
	public CodeGenerator(){
		
	}
	
	public String nodeCodeGenFront(Node node){
		switch(node.getNodeType()){
			default:
				return "";
				
			case HEADER:
				return "<h>";

			case LIST:
				break;
			case Q_BLOCK: 
				break;
			case BLOCK: 
				break;
			case HORIZONTAL_:
				break;
			case ORDER: 
				break;
			case UNORDER:
				break;
			case ITEM_LIST:
				break;		
		}
		return "";
	}
	
	public String nodeCodeGenBack(Node node){
		switch(node.getNodeType()){
			default:
				return "";
				
			case HEADER:
				return "</h>";

			case LIST:
				break;
			case Q_BLOCK: 
				break;
			case BLOCK: 
				break;
			case HORIZONTAL_:
				break;
			case ORDER: 
				break;
			case UNORDER:
				break;
			case ITEM_LIST:
				break;		
		}
		return "";
	}

}
