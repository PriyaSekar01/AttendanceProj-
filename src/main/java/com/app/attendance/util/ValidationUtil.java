package com.app.attendance.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.app.attendance.enumeration.UserRole;

public class ValidationUtil {
	private ValidationUtil() {

	}
	public static boolean isNullOrEmpty(String value) {
		return null == value || value.trim().isEmpty();
	}

	public static boolean isNull(UUID value) {
		return null == value;
	}

	public static boolean isNull(Double value) {
		return null == value || value == 0;
	}

	public static Boolean isValidPhoneNo(String phoneNo) {
		if (phoneNo.length() < 10 || phoneNo.length() > 10) {
			return true;
		}
		return false;
	}
	public static boolean isValidMobileNumber(String value) {
		String regex = "(?:\\s+|)((0|(?:(\\+|)91))(?:\\s|-)*(?:(?:\\d(?:\\s|-)*\\d{9})|(?:\\d{2}(?:\\s|-)*\\d{8})|(?:\\d{3}(?:\\s|-)*\\d{7}))|\\d{10})(?:\\s+|)";
		Pattern p = Pattern.compile(regex);
		String s = String.valueOf(value);
		Matcher m = p.matcher(s);
		return m.matches();
	}
	public static boolean isValidEmailId(String value) {
		String regex = "^(?=.{1,64}@)[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9-.]+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.matches();
	}

	public static boolean isNameValid(String name) {
		return name != null && !name.isEmpty();
	}

	public static boolean isValidName(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}

		// Define the allowed name pattern
		String namePattern = "^[\\p{L} '-]+$";

		// Check if the name matches the pattern
		if (!name.matches(namePattern)) {
			return false;
		}

		// Check for length constraints
		if (name.length() < 1 || name.length() > 50) {
			return false;
		}

		return true;
	}
	public static boolean isUserNameValid(String userName) {
		if (userName == null || userName.length() < 3 || userName.length() > 20) {
			return false;
		}
		return userName.matches("^[a-zA-Z][a-zA-Z0-9]+$");

//		IF THE USERNAME IS "USER123", IT WILL RETURN TRUE BECAUSE IT MEETS ALL THE CRITERIA.
//		IF THE USERNAME IS "USER!123", IT WILL RETURN FALSE BECAUSE IT CONTAINS A SPECIAL CHARACTER (!) WHICH IS NOT ALLOWED.
	}
	public static boolean isRolerequired(UserRole role) {
		return role == null;
	}

	public static boolean isRoleValid(UserRole role) {
		String roleTypetoString = role.toString();
		return roleTypetoString.equalsIgnoreCase("admin") || roleTypetoString.equalsIgnoreCase("user");
	}
	public static boolean isValidPassword(String password) {
		if (password == null) {
			return false;
		}

		String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";

		if (!Pattern.matches(passwordRegex, password)) {
			return false;
		}

		boolean hasSpecialChar = password.contains("@");

		return hasSpecialChar;
	}
	public static boolean isPasswordValid(String password, String confirmPassword) {
		// Check if both password and confirmPassword are not null and are equal
		if (password == null || confirmPassword == null || !password.equals(confirmPassword)) {
			return false;
		}

		// Regex that checks for the minimum requirements
		String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";

		// Use Pattern.matches to check if the password meets the regex requirements
		if (!Pattern.matches(passwordRegex, password)) {
			return false;
		}

		// Optional: Check for specific special characters if needed
		// For example, to ensure that the password contains at least one '@' character
		boolean hasSpecialChar = password.contains("@");

		return hasSpecialChar;
	}
	public static boolean isDateOfBirthrequired(String dateOfBirth) {
		return dateOfBirth == null || dateOfBirth.isEmpty();
	}
	public static boolean isValueNegative(Number values) {
		if (values instanceof Double) {
			Double value = (Double) values;
			if (value.doubleValue() < 0) {
				return true;
			}
		}
		if (values instanceof Integer) {
			Integer value = (Integer) values;
			if (value.intValue() < 0) {
				return true;
			}
		}
		return false;
	}
	public static String getFormattedString(String value) {
		if (value != null) {
			return value.trim();
		}
		return value;
	}

	public static boolean isDateOfBirthValid(String dob) {
		final String DATE_PATTERN = "dd-MM-yyyy";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
		LocalDate date = LocalDate.parse(dob, formatter);

		if (dob == null || dob.isEmpty()) {
			return false;
		}
		if (date.isAfter(LocalDate.now())) {
			return false;
		}
		return true;

	}

}
