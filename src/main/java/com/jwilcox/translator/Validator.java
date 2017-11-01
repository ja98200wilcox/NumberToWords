package com.jwilcox.translator;

import java.util.regex.Pattern;

/**
 * Used to validate input received through the console.
 * @author Jim Wilcox
 *
 */
public class Validator {
	
	private static final Pattern numberPattern = Pattern.compile("^-?\\d+$");
	
	public static final long NUMBER_MIN = -999999999999999L;
	public static final long NUMBER_MAX = 999999999999999L;
	
	/**
	 * Check if number is greater than or equal to the allowed Minimum
	 * @param value a number to be tested
	 * @param min the minimum value to check against
	 * @return true if greater than or equal to minimum
	 */
	public static boolean isGreaterThanOrEqualToMin(long value, long min) {
		if(min > value) {
			return false;
		}
		return true;
	}
	
	/**
	 * Check if number is less than or equal to the allowed Maximum
	 * @param value a number to be tested
	 * @param max the maximum value to check against
	 * @return true if less than or equal to maximum
	 */
	public static boolean isLessThanOrEqualToMax(long value, long max) {
		if(value > max) {
			return false;
		}
		return true;
	}
	
	/**
	 * Is the value a Number
	 * @param value to be tested if it is a Number.   
	 * @return true if value only contained digits or digits starting with minus sign
	 */
	public static boolean isNumber(String value) {
		return numberPattern.matcher(value).matches();
	}
	
	/**
	 * Group method using all or most of the methods provided here. 
	 * @param value to be checked if valid
	 * @return true if is a number and between min and max 
	 */
	public static boolean isValid(final String value) {
		if(!Validator.isNumber(value)) {
			return false;
		}
		long valueLong = Long.valueOf(value);
		if(!Validator.isGreaterThanOrEqualToMin(valueLong,NUMBER_MIN)) {
			return false;
		}
		if(!Validator.isLessThanOrEqualToMax(valueLong, NUMBER_MAX)) {
			return false;
		}
		return true;
	}
}
