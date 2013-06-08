package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Saurabh
 */

public class RegexMatcher {

	public static String match(String input,String regularexpression) {
        Pattern p = Pattern.compile(regularexpression, 
        		Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(input);
        if (m.find())
        {
            String match = m.group(0);
            return match;
        }
        return null;
    }

	
	


}
