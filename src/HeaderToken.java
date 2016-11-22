class HeaderToken extends Token{
	// return type header
	// 8 types of header
	// #, ##, ###, ####, #####, ######
	// =======, -------
	public void accept(Visitor v) {
		v.visitToken(this);
	}
}