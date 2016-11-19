public class Main {
	public static void main(String[] args) {
		Help h = new Help( );
		CLI cli = new CLI( );
	
		if (args.length == 0) {
			System.out.println("No input");
			h.help();
			System.exit(0);
		}
		if(args.length == 1){
			if(args[0] .equals("help") || args[0] == "-h") {
				h.help();
				System.exit(0);
			}
		}
		cli.inputParser(args);
	}
}