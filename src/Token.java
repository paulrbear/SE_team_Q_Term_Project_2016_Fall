public class Token extends Node{
	String tokenString;
	public Token(){
		
	}
	public Token(String str){
		setString(str);
	}
	
	public void setString(String str){
		tokenString = str;
	}
	public String getString(){
		return tokenString;
	}

}
