
public class PlainToken extends Token {
	public void accept(Visitor v){
		v.visitPlainToken(this);
	}

}
