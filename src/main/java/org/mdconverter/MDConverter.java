import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.w3c.tidy.Tidy;

public class MDConverter{
	public static void main(String[] args) {
		
		CLI cli = new CLI(args);
		MDParser p = new MDParser(cli.filelist.get(0)); //TODO : multiple input
		Document doc = p.getDoc();
		Visitor v = null;
		
		switch(cli.styleVariable){
		case 1:
			v = new PlainVisitor();
			doc.accept(v);
			break;
		case 2:
			break;
		case 3:
			break;
		default:
			break;
		}
		System.out.println(v.getDocument());
		jtidy(v.getDocument(),cli.outputFile);
	}
	
	private static void jtidy(String HTMLCode,String fileName){
		Tidy tidy = new Tidy();
		InputStream is = null; 
		try{		
			is = new ByteArrayInputStream(HTMLCode.getBytes("UTF-8"));
//			tidy.setXHTML(true); 
//	        tidy.setDocType("\"-//W3C//DTD XHTML 1.0 Transitional//EN\""); 
	        tidy.setInputEncoding("UTF-8");
/*	        tidy.setQuiet(true); 
	        tidy.setShowWarnings(false); 
	        tidy.setIndentContent(true); 
	        tidy.setSmartIndent(true); 
	        tidy.setIndentAttributes(true); 
	        tidy.setWraplen(0);
*/	        ByteArrayOutputStream out = new ByteArrayOutputStream(1024); 
	        tidy.parse(is, out); 
	        FileOutputStream fos = new FileOutputStream(new File(fileName));
	        out.writeTo(fos);
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
		   e.printStackTrace();
		}
	}
}