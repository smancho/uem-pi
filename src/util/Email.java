package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	
	public static boolean validateEmail(String emailStr) {
		String s = emailStr.trim();
		if (s.length() == 0 || Strings.EMPTY_STRING.equals(s)) {
			return false;
		}
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
	}

}
