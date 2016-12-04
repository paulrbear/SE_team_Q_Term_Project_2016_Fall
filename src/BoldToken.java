public class BoldToken extends Token {
	
	public BoldToken(String str){
		tokenString = str;
	}
	public void accept(Visitor v){
		v.visitBoldToken(this);
	}

}
