package mdconverter;
import java.io.*;

class Help {
	public static void help() throws IOException{
		System.out.println("Help messages");
		File file = new File("README.md");
			String line = null;
			
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
			
				while((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
				} bufferedReader.close();  
			
		
		System.exit(1);
	}
}
