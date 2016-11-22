
public class Token {
	
	// Token type들을 나열한 Enum list
	public enum TokenType{
		PLAIN, STYLE, HTML, LINK, IMAGE, ITEM_LIST, HEADER, Q_BLOCK;	
	}
	
	//ATTRIBUTES!!!
	private TokenType tokenType;	// token type
	public String newString; 		// markdown syntax가 제거된 raw string이다.
		
	//OPERATIONS!!!
	public void setTokenType(TokenType tt)
	{
		this.tokenType = tt;
	}
	
	public TokenType getNodeType()
	{
		return tokenType;
	}

	public void simplifyString(String s)
	{

		newString = "";
	}
}