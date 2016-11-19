import java.io.*;
class Help {
	public void help(){
		System.out.println("Help messages");
		File file = new File("README.md");
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(file);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}
			// Always close files.
			bufferedReader.close();  
		} catch(IOException ex) {
			System.out.println("Error reading file '" + file + "'"); 
		}
	}
}