import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class CLI {
    
    private static void helpMe(){
        System.out.println("this is help me");
    }
    
    private static void inputParser(String[] input){
        
        String[] inputFile;
        String outputFile ="";
      //  String style;
        int styleVariable=1;
        int inputIndex=-1;
        int outputIndex=-1;
        int styleIndex=-1;
        boolean order;
        inputFile = new String[10];
        
        int n=0;
        for(int i=0;i<input.length;i++){
        	if(input[i].contains(".md")){
        		inputFile[n]=input[i];
        		inputIndex = i;
        		n++;
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
        			System.out.println("스타일 옵션은 -p -f -s 중 하나여야 합니다.");
        			break;
        		}
        	}else{
        		outputIndex = i;
        		if(input[i].contains(".html")){
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
            	System.out.println("순서가 잘못됐습니다. 순서는 inputfileName style outputfilename 순 입니다.");
            }
        }
        else{
        	System.out.println(".md파일이 없습니다. ");
        }
        
    	
    }
   
	
    public static void main(String[] args) {
        if(args.length==1){
        	if(args[0]=="help" || args[0]=="-h")helpMe();
        }
    	inputParser(args);
    }
}

//file 존재 여부 체크
//안되는 문자 거르고
