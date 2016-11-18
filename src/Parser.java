import java.io.*;

class Parser {
	public static void parser(File inputFile) {
       		String line = null;

     		try {
            			// FileReader reads text files in the default encoding.
            			FileReader fileReader = new FileReader(inputFile);

            			// Always wrap FileReader in BufferedReader.
		            	BufferedReader bufferedReader = new BufferedReader(fileReader);

            			while((line = bufferedReader.readLine()) != null) {
               			System.out.println(line);
            			}
            			 // Always close files.
            			bufferedReader.close();  
        		}
        		catch(IOException ex) {
           			System.out.println("Error reading file '" + inputFile + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
       		}
	}
}