public class ImageToken extends Token {

	public ImageToken(String name, String dir){
		tokenString = name;
		tokenDir = dir;
	}
	public void accept(Visitor v){
		v.visitImageToken(this, this);
	}
	
}
