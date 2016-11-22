public class Token {
	// visitor가 token class 방문


	public String tokenString = NULL;

	// Token type들을 나열한 Enum list
	public enum TokenType{
		PLAIN, STYLE, HTML, LINK, IMAGE, ITEM_LIST, HEADER, Q_BLOCK;	
	}
	
	//ATTRIBUTES!!!
	private TokenType tokenType;	// token type
		
	//OPERATIONS!!!
	public void setTokenType(TokenType tt)
	{
		this.tokenType = tt;
	}
	
	public TokenType getTokenType()
	{
		return tokenType;
	}

	public void accept(Visitor v) {
		v.visitToken(tokenType);
	}

	public getString( String s) {
		tokenString = s;
		return tokenString;
	}
}