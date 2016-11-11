import java.io.*;

public class CLI {
    private static void helpMe(){
        System.out.println("this is help me");
    }

    private static void inputParser(String[] input){
        String[] inputFile = new String[10];
        String outputFile ="";
         //  String style;
        int styleVariable=1;
        int inputIndex=-1;
        int outputIndex=-1;
        int styleIndex=-1;
        boolean order;

        int n=0;
        for(int i=0;i<input.length;i++){
        	if(input[i].contains(".md")){
				if(input[i].substring(input[i].length()-3, input[i].length()).equals(".md")){
        			inputFile[n]=input[i];
        			File file = new File (inputFile[n]);
        			if (!file.exists()) {
        				System.out.println("File does not exist");
        				System.exit(0);
					}
        			inputIndex = i;
        			n++;
        		}else{
					System.out.println("inappropriate file extension");
					helpMe();
					System.exit(0);
        		}
        	}else if(input[i].contains("-")){
        		styleIndex = i;
        		switch(input[i]){
        		case "-p": //plain option
        			styleVariable=1;
        			break;
        		case "-f": //fancy option
        			styleVariable=2;
        			break;
        		case "-s": //slide option
        			styleVariable=3;
        			break;
        		default:
        			System.out.println("Possible Style Options are : -p -f -s");
        			helpMe();
        			System.exit(0);
        		}
        	}else if(input[0].contains(".md")){
        		outputIndex = i;
				// < > : " / \ | ? *
				//system does not allow < > | *
				//system permits : " / \ ? 
			        // CON, PRN, AUX, NUL
			    if (
				//input[i].contains("\u003C") || // <
				//input[i].contains("\u003D") || // >
				input[i].contains("\u003A") || // :
				input[i].contains("\"") || // "
				//\u0022
				input[i].contains("\u002F") || // /
				input[i].contains("\\") || // \
				//\u005C
				//input[i].contains("\u007C") || // |
				input[i].contains("\u003F") || // ? 
				input[i].contains("\u002A") ) // * 
			     { 
				System.out.println("Invalid character in output file name");
				System.out.println("Refrain from using the following characters: \u003C \u003D \u003A \" \u002F \\ \u003F \u002A \n");


				helpMe();
				System.exit(0);
			}
				if (input[i].equalsIgnoreCase("CON") ||
					input[i].equalsIgnoreCase("PRN") ||
					input[i].equalsIgnoreCase("AUX") ||
					input[i].equalsIgnoreCase("NUL")
				) {
					System.out.println("illegal output file name");
					helpMe();
					System.exit(0);
				}
        		if (outputIndex != 1) {
					System.out.println("No output file name");
					helpMe();
					System.exit(0);
				}
        		if((input[i].length() >= 5) && input[i].substring(input[i].length()-5, input[i].length()).equals(".html")){
        			outputFile=input[i];
        		}else{
        			outputFile=input[i]+".html";
              }
           }
        }
        if(inputIndex!=-1){
           //order check
            if (styleIndex==-1){
               order = inputIndex < outputIndex;
            }
            else{
               order = (inputIndex < styleIndex) && (styleIndex < outputIndex);
            }
            if(order){
               //file export
            try {
               File file = new File(outputFile);
               file.createNewFile();
            } catch(IOException io) {
               System.out.println("cannot create html file");
            }
               System.out.println("file export executed");
               System.out.println(inputIndex +" "+ styleIndex+" " + outputIndex);
               for(int i=0;i<input.length;i++){
                  System.out.println(input[i]);
               }
               for(int i=0;i<n;i++){
                  System.out.println(inputFile[i]);
               }
               System.out.println(outputFile);
            }
            else{
               System.out.println("Syntax Error : [inputFileName], [style], [outputFileNmae]");
            }
        }
        else{
           System.out.println(".md file does not exist.");
        }
    }

    public static void main(String[] args) {
		if (args.length==0) {
			System.out.println("No input");
			helpMe();
			System.exit(0);
		}
        if(args.length==1){
           if(args[0]=="help" || args[0]=="-h")helpMe();
        }
       inputParser(args);
    }
}

//check if file exists
//invalid character
