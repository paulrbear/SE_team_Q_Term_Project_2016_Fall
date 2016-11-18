public class FileReader {
	public static void readFile() {
	    	File file = new File(inputFile[n]);

		if (!file.exists()) {
			System.out.println("File does not exist");
			System.exit(0);
		}
		inputIndex = i;
		n++;
		parser(file);
	}
}