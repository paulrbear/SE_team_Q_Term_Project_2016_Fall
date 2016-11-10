import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader {
	
	public static void main(String args[]) throws IOException
	{
		int r = 1;
		while(r > 0){
			
			// Enter input filename.
			System.out.println("Enter name of a md file name: ");
			Scanner input = new Scanner(System.in);
			String filename = input.nextLine();		
			if(validInput(filename) > 0)
			{
				continue;
			}
			
			// Enter output filename.
			System.out.println("Enter name of an output file: ");
			String filename_out = input.nextLine();		
			if(validInput(filename) > 0)
			{
				continue;
			}
			
			// Enter the style of the file.
			System.out.println("Enter style of the output file: ");
			String style = input.nextLine();		
			if(validInput(filename) > 0)
			{
				continue;
			}
			
		}
		
	}
	
	static int validInput(String s)
	{
		char dotsplit = '.';
		int result = 0;
		// filename input Ã³¸®
		if(s.isEmpty())
		{
			System.out.println("File name has not been entered. Enter the file name");
			result = 1;
		}
		// filename that does not contain its type:
		else if(s.indexOf(dotsplit)==0) // invalid because it does not have filename
		{
			System.out.println("Inappropriate file name! Please type a filename in following format:");
			System.out.println("file_name.file_type");
			result = 2;
		}
		else if(s.indexOf(dotsplit)==-1) // does not contain file type
		{
			System.out.println("please contain the file type.");
			result = 3;
		}
		
		return result;
	}
	
}
