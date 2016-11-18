
public class Token {
	
	// Token type들을 나열한 Enum list
	public enum TokenType{
		// TO-DO 
			// implement the details
			// add more types (더 있으면..)
		PLAIN, STYLE, HTML, LINK, IMAGE, ITEM_LIST, HEADER, Q_BLOCK;	
	    static {
	    }
	}
	
	//ATTRIBUTES!!!
	private TokenType tokenType;	// token의 type
	public String newString; // markdown syntax가 제거된 raw string이다.
		
	
	//OPERATIONS!!!
	public void setTokenType(TokenType tt)
	{
		this.tokenType = tt;
	}
	
	public TokenType getNodeType()
	{
		return tokenType;
	}

	public void simplifyString(String s)//md syntax를 없애고(가공하는)method이다.
	{
		// TO-DO
			// implement the details.
		newString = "";
	}
	
	public Token createToken(String s)
	{
		// TO-DO
			// 수정할 것.
		return new Token();
	}
	
	
	
	
	
	
	public static void main(String[] args)
	{
		//TO-DO 
		Token token = new Token();
	}

}
