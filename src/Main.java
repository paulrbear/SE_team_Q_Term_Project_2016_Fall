public class Main {
	public static void main(String[] args) {
		if (args.length==0) {
			System.out.println("No input");
			helpMe();
			System.exit(0);
		}
	        	if(args.length==1){
	           		if(args[0]=="help" || args[0]=="-h")
	           			helpMe();
	        	}
	       	inputParser(args);
    	}
	private static void helpMe(){
		System.out.println("this is help me");
    	}
	// call CLI inputParser
	// call fileReader
    	// call sendtonode
}