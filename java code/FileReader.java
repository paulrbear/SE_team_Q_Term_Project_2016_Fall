import java.io.*;
import java.util.Scanner;

public class FileReader {
	public static void main(String args[]) throws IOException {
		// command line typing format: java FileReader (input_file) (output_file) (style)
		Scanner input = new Scanner(System.in);
		String input_file = args[0];
		String output_file = args[1];
		String style = args[2];

		if (input_file.equalsIgnoreCase("help") || output_file.equalsIgnoreCase("help") || style.equalsIgnoreCase("help")) {
			System.out.println("help message");
			System.exit(1);
		}

		while (!input_file.substring(input_file.length()-3, input_file.length()).equals(".md")) {
			System.out.println("Not valid input file name");
			System.out.println("Enter input file name: ");
			input_file = input.nextLine();
			if (input_file.equalsIgnoreCase("help")) {
				System.out.println("help message");
				System.exit(1);
			}
		}

		while (!output_file.substring(output_file.length()-5, output_file.length()).equals(".html")) {
			System.out.println("Not valid output file name");
			System.out.println("Enter output file name: ");
			output_file = input.nextLine();
			if (output_file.equalsIgnoreCase("help")) {
				System.out.println("help message");
				System.exit(1);
			}
		}

		while (!style.equalsIgnoreCase("fancy") && !style.equalsIgnoreCase("plain") && !style.equalsIgnoreCase("slide")) {
			System.out.println("Not valid style name");
			System.out.println("Enter style name: ");
			style = input.nextLine();
			if (style.equalsIgnoreCase("help")) {
				System.out.println("help message");
				System.exit(1);
			}
		}

		// read input file and output file
		// check whether input file exists or not
	}
}
