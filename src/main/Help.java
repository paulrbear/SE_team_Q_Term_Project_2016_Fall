package main;
import java.io.*;

class Help {
	public static void help(){
		System.out.println("Help messages");
		File file = new File("README.md");
		if (!file.exists()) {
			System.exit(0);
		}
		else {
			String line = null;

			try {
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				while((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
				} bufferedReader.close();  
			} catch(IOException ex) {
				System.out.println("Error reading file '" + file ); 
			}
		}
		System.exit(0);
	}
}
