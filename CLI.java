import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader {
    
    private static void helpMe(){
        System.out.println("this is help me");
    }
    
    private static void inputParser(String input){
        
        String inputFile;
        String outputFile;
        String style;
        int styleVariable=1;
        int inputIndex=input.indexOf(".md");
        int outputIndex=input.indexOf(".html");
        int styleIndex=-1;
        boolean order;
        
        //set style Type and index
        if(input.matches("-p")){
            styleIndex=input.indexOf("-p");
            styleVariable = 1;
        }else if(input.matches("-f")){
            styleIndex=input.indexOf("-f");
            styleVariable = 2;
        }else if(input.matches("-s")){
            styleIndex=input.indexOf("-s");
            styleVariable = 3;
        }
        //check order
        order = inputIndex<outputIndex && outputIndex<styleIndex;
        if (!order){
            System.out.println("no order");
            System.exit(0);
        }
        //set input file name and output file name.
        if(order&&inputIndex>0&&outputIndex>0){
            inputFile=input.substring(0, inputIndex+2);
            outputFile=input.substring(inputIndex+4,outputIndex+4);
            System.out.println("success!");
        }
        
        if(!input.matches(".md")){
            System.out.println("no .md");
        }
        if(!input.matches(".html")){
            System.out.println("no .html");
        }
        
    }
    
    public static void main(String[] args) {
        String input = "";
        for(int i=0;i<args.length;i++){
            input=input + args[i];
            input=input + " ";
        }
        
        System.out.print(input);
        if(input==""){
            System.out.println("아무것도 안넣었음");
            helpMe();
        }
        
        if(input == "help"){
            helpMe();
            
        }
        inputParser(input);
        
    }
}

//file 존재 여부 체크
//안되는 문자 거르고
