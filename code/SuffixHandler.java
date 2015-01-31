package code;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SuffixHandler {
	
	static int currentFileCount = 5;
	
	public SuffixHandler() {

	}

	public static boolean checkFormat(String format){
		
		boolean openSign = false;
		
		char[] chars = format.toCharArray();
		
		for (int i =0; i < chars.length; i++){
			if (chars[i] == '<'){
				
				openSign = true;
				
				for (int j = i+1; j < chars.length; j++){
					if(chars[j] == '>'){
						openSign = false;
						//say(format.substring(i, j+1));
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
	
	
	public String getSuffix(String format) {
		char[] chars = format.toCharArray();
		String output = "";
		boolean numCountUsed = false;
		
		for (int i =0; i < chars.length; i++){
			
			if (chars[i] == '<'){
				for (int j = i; j < chars.length; j++){
					if(chars[j] == '>'){
						
						//String containerInclusive = format.substring(i, j+1);
						String containerExclusive = format.substring(i+1, j);
						output+= toTimeStamp(containerExclusive);
						
						i = j;
						break;
					}
				}
			}
			else if (chars[i] == '#'){
				
				int count = 0;
				int newI = 0;
				
				for (int j = i; j < chars.length; j++){
					if(chars[j] == '#'){
						count++;
					}
					else{
						break;
					}
					newI = j;
				}
				
				i = newI;
				output += String.format("%0" + count + "d", currentFileCount);
				numCountUsed  = true;
			}
			else{
				output+= chars[i];
			}
		}
		
		if (numCountUsed)
			currentFileCount++;
		
		return output;
		
	}
	
	private static String toTimeStamp(String format){
		
		return new SimpleDateFormat(format).format(Calendar.getInstance(Locale.getDefault()).getTime());
		
	}

	public static void say(Object s){
		System.out.println(s);
	}

	private static void err(Object s) {
		System.err.println(s);
	}

//	public static void main(String[] args) {
//			
//		//<Timestamps>     ## -Incrementing Numbers
//		
//		//MM & M = month # //Differce is 01 vs 1
//		//MMMM = month Name
//		//dd = day #
//		//y = year // y=2014, yy= 14, yyy = 14, yyyy = 2014
//		//HH -hours //mm = minutes //ss - seconds
//		
//		//http://developer.android.com/reference/java/text/SimpleDateFormat.html
//		
////		The following reserved characters: (Windows)
////			< (less than)
////			> (greater than)
////			: (colon)
////			" (double quote)
////			/ (forward slash)
////			\ (backslash)
////			| (vertical bar or pipe)
////			? (question mark)
////			* (asterisk)
//		
//		String format = "Date:<MMM-dd-yy>---#Hello#-<mm>-####";
//		
//		if(checkFormat(format)){
//			say(getSuffix(format));
//			format = "######QQ<DD>HI";
//			say(getSuffix(format));
//			format = "poop";
//			say(getSuffix(format));
//			format = "#";
//			say(getSuffix(format));
//		}
//	}
}


