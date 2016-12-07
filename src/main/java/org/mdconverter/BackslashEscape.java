package main;
public class BackslashEscape {

	public String BackslashEscape(String checkString){
		//Backslash			
			/*
			/////////////////////////////////////////

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
			
			////////////////////////////////////////
			*/
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
		
		//Char that are not quite BE but needs replacing
		// "<" -> &lt, "&" -> &amp
		checkString.replaceAll("\\<", "\\&lt");
		checkString.replaceAll("\\&", "\\&amp");
		
		//return result
		return checkString;
	}
}

