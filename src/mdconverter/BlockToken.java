package mdconverter;

public class BlockToken extends Token{

	public enum TokenStyle{
		Plain, Bold, Italic;
	}
	
	public TokenStyle tokenStyle;
	
	public BlockToken(String str, TokenStyle style){
		tokenString = str;
		setStyle(style);
	}
	
	public void setStyle(TokenStyle style){
		tokenStyle = style;
	}
	
	public TokenStyle getStyle(){
		return tokenStyle;
	}
	
	public void accept(Visitor v){
		//v.visitBlockToken(this);
	}
}
