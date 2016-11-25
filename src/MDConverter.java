public class MDConverter{
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
				System.exit(0);public class MDConverter{
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
						
				//test	System.out.println("before input Parser");
						cli.inputParser(args); 
						//TODO: CLI cli = new CLI(args);
						//TODO: CLI:MDParser p = new MDParser(cli.transferData());
						//TODO: PlainVisitor v = new Plainvistor(Document doc);
						//TODO: Jtidy.check(v.HTMLCode);
						//TODO: filewrite(v.HTMLCode);
						//cli.fileWrite(cli.p.getHTML(),cli.outputFile);
					}
				}
			}
		}
		System.out.println("before input Parser");
		cli.inputParser(args);
		//cli.fileWrite(cli.p.getHTML(),cli.outputFile);
	}
}