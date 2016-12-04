public class ItalicToken extends Token {
	
	public ItalicToken(String str){
		tokenString = str;
	}
	public void accept(Visitor v){
		v.visitItalicToken(this);
	}

}
