public class BackslashEscape {

	public String BlackslashEscape(String checkString){
		checkString.replaceAll("(\\)(\\*)","(\\*)");
		checkString.replaceAll("(\\)(\\)","(\\)");
		checkString.replaceAll("(\\)(\\')","(\\')");
		checkString.replaceAll("(\\)(\\_)","(\\_)");
		checkString.replaceAll("(\\)(\\{)","(\\{)");
		checkString.replaceAll("(\\)(\\})","(\\})");
		checkString.replaceAll("(\\)(\\[)","(\\[)");
		checkString.replaceAll("(\\)(\\])","(\\])");
		checkString.replaceAll("(\\)(\\()","(\\()");
		checkString.replaceAll("(\\)(\\))","(\\))");
		checkString.replaceAll("(\\)(\\#)","(\\#)");
		checkString.replaceAll("(\\)(\\.)","(\\.)");
		checkString.replaceAll("(\\)(\\!)","(\\!)");
		return checkString;
	}
}


/*
"<" -> &lt, "&" -> &amp 


\* literal asterisks \*
Markdown provides backslash escapes for the following characters:
\ backslash
бо backtick
* asterisk
_ underscore
{} curly braces
[] square brackets
() parentheses
# hash mark
. dot
! exclamation mark


*/