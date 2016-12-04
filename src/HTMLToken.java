public class HTMLToken extends Token {

	public HTMLToken(String name, String dir){
		tokenString = name;
		tokenDir = dir;
	}
	public void accept(Visitor v){
		v.visitHTMLToken(this);
	}
	
}
