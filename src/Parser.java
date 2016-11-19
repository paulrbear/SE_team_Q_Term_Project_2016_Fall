import java.io.*;

class Parser {
	public void parser(File Inputfile) {
		String line = null;

		try {
			FileReader fileReader = new FileReader(Inputfile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}
			bufferedReader.close();  
		} catch(IOException ex) {
			System.out.println("Error reading file '" + Inputfile + "'"); 
		}
	}
}