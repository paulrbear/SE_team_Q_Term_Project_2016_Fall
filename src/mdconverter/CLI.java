package mdconverter;
import java.io.*;
import java.util.ArrayList;

public class CLI  {
	public static String outputFile ="";
//	public MDParser p = new MDParser( );
	public static int styleVariable=1;
	public static ArrayList<File> filelist = new ArrayList<File>();
	
	public CLI(String[] input) throws IOException{
		cliCheck(input);
	}
	
	public static void cliCheck(String[] input) throws IOException{
		if (input.length == 0) {
			Help.help();
		}
		if(input.length == 1){
			if(input[0] .equals("help") || input[0] == "-h") {
				Help.help();
			}
		}
		inputParser(input);
	}
	public static void inputParser(String[] input) throws IOException{
		//Help h = new Help( );
		
		int inputIndex=-1;
		int outputIndex=-1;
		int styleIndex=-1;
		int numOfOption = 0;
		int numOfOutput = 0;
		boolean order;
		
		for(int i=0; i < input.length; i++) {
			if(input[i].contains(".md")){
				File e = new File(input[i]);
				if (e.isFile()) {
					filelist.add(e);
					System.out.println(input[i]+" is added into converter que");
					inputIndex=i;
				}
				else{
					System.out.println("file does not exist:"+input[i]);
				}
			}else if(input[i].contains("-")){
				if(numOfOption==0){
					setStyle(input[i]);
					numOfOption++;
					styleIndex=i;
				}else{
					System.out.println("only one option is allowed");
					Help.help();
				}
			}else if(!filelist.isEmpty()){
				
				if (numOfOutput == 0){
					// < > : " / \ | ? *
					if (
						 //input[i].contains("\u003C") || // <
						 //input[i].contains("\u003D") || // >
						 input[i].contains("[:]") || // :
						 input[i].contains("\"") || // "
						 //\u0022
						 input[i].contains("\\\\") || // /
						 input[i].contains("\\") || // \
						 //\u005C
						 //input[i].contains("\u007C") || // |
						 input[i].contains("?") || // ?
						 input[i].contains("[*]") ) // *
					 {
						System.out.println("Invalid character in output file name");
						System.out.println("Refrain from using the following characters: \u003C \u003D \u003A \" \u002F \\ \u003F \u002A \n");
						Help.help();
					}
					if (
						input[i].equalsIgnoreCase("CON") ||
						input[i].equalsIgnoreCase("PRN") ||
						input[i].equalsIgnoreCase("AUX") ||
						input[i].equalsIgnoreCase("NUL")
					) {
						System.out.println("illegal output file name");
						Help.help();
					}
					setOutputFilename(input[i]);
					outputIndex = i;
					numOfOutput++;
				} else {
					System.out.println("Only one output is allowed");
					Help.help();
				}
			}
			
		}
		order = orderCheck(inputIndex,styleIndex,outputIndex);
		
		if(order){
			makeFile();
		}
		else{
 			System.out.println("Syntax Error : [inputFileName], [style], [outputFileNmae]");
 			System.exit(0);
		}
	}
	
	private static void makeFile() {
		try {
			File file = new File(outputFile);
			if (file.exists()) {
				System.out.println("Output file already exists");
				System.exit(0);
			}
			file.createNewFile();
		} catch(IOException io) {
			System.out.println(io);
		}
		System.out.println(outputFile+" is created.");
	}

	private static boolean orderCheck(int inputIndex, int styleIndex, int outputIndex) {
		
		if (styleIndex == -1){
			return inputIndex < outputIndex;
		}
		else{
			return (inputIndex < styleIndex) && (styleIndex < outputIndex);
		}
		
	}
	
	private static void setOutputFilename(String string) {
		// TODO Auto-generated method stub
		if((string.length() >= 5) && string.substring(string.length()-5, string.length()).equals(".html")){
			outputFile=string;
		}else{
			outputFile=string+".html";
		}
		
	}
	public ArrayList<File> getFileList(){
		return filelist;
	}
	public int getStyleVariable(){
		return styleVariable;
	}
	public String getOutputFile(){
		return outputFile;
	}
	public static void setStyle(String string) throws IOException {
		switch(string){
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
			Help.help();
			System.exit(0);
	}
		
	}
}
