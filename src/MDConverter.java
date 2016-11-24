import org.w3c.tidy.Tidy;

public class MDConverter{
	public static void main(String[] args) {
		Help h = new Help( );
		CLI cli = new CLI( );
		
		Tidy tidy = new Tidy();
		tidy.setXHML(boolean xhtml);
		tidy.parse(inputStream, System.out);
	
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