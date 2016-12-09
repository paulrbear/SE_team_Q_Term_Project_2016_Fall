package mdconverter;
public class BackslashEscape {

	public String BackslashEscapes(String checkString){
		//Backslash			
			/*
			/////////////////////////////////////////

			Markdown provides backslash escapes for the following characters:
			\ backslash
			�� backtick
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
		
		checkString.replaceAll("\\<", "\\&lt");
		checkString.replaceAll("\\&", "\\&amp");
		
		//return result
		return checkString;
	}
}

