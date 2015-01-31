package code;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class Validate {

	public Validate() {
		
	}
	
	public boolean checkTimeInput(String time){
//		Pattern p = Pattern.compile("\\d*:\\d*:\\d*");
//		Matcher m = p.matcher(time);
//		return m.matches();
		return Pattern.matches("\\d*:\\d*:\\d*", time);
	}
	
	public boolean checkSourceInput(File file){
		return file.exists();
	}
	public boolean checkSourceInput(String s){
		return new File(s).exists();
	}
	
	public boolean checkDestInput(File file){
		return new File(file.getPath().toString().substring(0, 2)).exists();
	}
	public boolean checkDestInput(String s){
		return new File(s.substring(0, 2)).exists();
	}
	public boolean checkSuffixInput(String format){
		return (checkTimeStamp(format) && !checkForIllegalChars(format));
	}
	
	public boolean checkForIllegalChars(String format) {
		return checkIfStringContainsMultiple(format, ':', '"', '/', '\\', '|', '?', '*', '^'); //Also '<' and '>' but used for timestamp
	}

	private boolean checkIfStringContainsMultiple(String format, char ... chars) {
		
		for (char c : chars)
			if (format.indexOf(c) >= 0)
				return true;
		
		return false;
	}

	private boolean checkTimeStamp(String format){
		boolean openSign = false;
		
		char[] chars = format.toCharArray();
		
		for (int i =0; i < chars.length; i++){
			if (chars[i] == '<'){
				
				openSign = true;
				
				for (int j = i+1; j < chars.length; j++){
					if(chars[j] == '>'){
						openSign = false;
						if (!ifTimeStampValid(format.substring(i, j+1)))
							return false;
						i = j;
						break;
					}
					else if(chars[j] == '<'){
						err("Nested <>");
						return false;
					}
				}
			}
			else if (chars[i] == '>'){
				err("> without a <");
				return false;
			}
		}
		
		
		if (openSign){
			err("Missing >");
			return false;
		}
		
		say("No TimeStamp Issues Found");
		return true;
	}
	
	private static boolean ifTimeStampValid(String format){
		try{
			new SimpleDateFormat(format);
			say("(" + format + ") is a Valid time stamp");
			return true;
		}
		catch (Exception e){
			err("(" + format + ") is not a Valid time stamp");
			return false;
		}
	}
	
	public static void main (String args[]){
		
		say(new Validate().checkTimeInput("45::89"));
		
		say(new Validate().checkDestInput("C:\\sdfsdf"));
		say(new Validate().checkDestInput("Q:\\sdfsdf"));
		
	}
	
	public static void say(Object s){
		System.out.println(s);
	}

	private static void err(Object s) {
		System.err.println(s);
	}

}
