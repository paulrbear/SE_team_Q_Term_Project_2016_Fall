import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader {
	public static void main(String args[]) throws IOException {
		Scanner input = new Scanner(System.in);

		String input_file = args[0];
		String output_file = args[1];
		String style = args[2];

		while (!input_file.substring(input_file.length()-3, input_file.length()).equals(".md")) {
			System.out.println("Not valid input file name");
			System.out.println("Enter input file name: ");
			input_file = input.nextLine();
		}

		System.out.println();
		while (!output_file.substring(output_file.length()-5, output_file.length()).equals(".html")) {
			System.out.println("Not valid output file name");
			System.out.println("Enter output file name: ");
			output_file = input.nextLine();
		}

		System.out.println();
		while (!style.equalsIgnoreCase("fancy") && !style.equals("plain") && !style.equals("slide")) {
			System.out.println("Not valid style name");
			System.out.println("Enter style name: ");
			style = input.nextLine();
		}

		// String help = "help";
		// check validity of file name and style

	}
}
