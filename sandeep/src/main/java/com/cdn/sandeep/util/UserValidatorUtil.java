package com.cdn.sandeep.util;

import java.util.regex.Pattern;

import com.cdn.sandeep.model.User;

public class UserValidatorUtil {

	public static boolean isEmailNotValid(String email) {
		final Pattern EMAIL_REGEX = Pattern.compile(
				"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
				Pattern.CASE_INSENSITIVE);
		return !EMAIL_REGEX.matcher(email).matches();
	}

	public static boolean isPasswordNotValid(String password) {
		boolean passwordValid=false;
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
		passwordValid	=password.matches(pattern);

		return !passwordValid;
	}

	public static boolean isAnyUserAttributeIsNull(User user) {
	
		if(user.getEmail()==null||
				user.getFirstName()==null||
				user.getLastName()==null||
				user.getPassword()==null
				) {
			return true;
		}
		
		return false;
		
		
		
	}

	// regex from password

}
