package com.jwilcox.translator;

/**
 * Converts numbers into words.
 * 
 * @author Jim Wilcox
 *
 */
public class ConvertImpl implements Convert {

	private static final String[] messageNumbers = { "", "one", "two", "three", "four", "five", "six", "seven", "eight",
			"nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen" };

	private static final String[] messageByTens = { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy",
			"eighty", "ninety" };

	private static final String MESSAGE_TRILLION = "trillion";
	private static final String MESSAGE_BILLION = "billion";
	private static final String MESSAGE_MILLION = "million";
	private static final String MESSAGE_THOUSAND = "thousand";
	private static final String MESSAGE_HUNDRED = "hundred";
	private static final String MESSAGE_MINUS = "minus";
	private static final String MESSAGE_ZERO = "zero";

	private static final long TRILLION = 1000000000000L;
	private static final long BILLION = 1000000000L;
	private static final long MILLION = 1000000L;
	private static final long THOUSAND = 1000L;
	private static final long HUNDRED = 100L;

	/**
	 * Converts number into words.
	 * 
	 * @param number
	 *            to be turned into words
	 * @return the number as words
	 * @throws IllegalArgumentException
	 *             if {@code value <= Validator.VALUE_MIN} or
	 *             {@code value <= Validator.VALUE_MAX}
	 */
	@Override
	public String convert(long number) {

		if (!Validator.isGreaterThanOrEqualToMin(number, Validator.NUMBER_MIN)) {
			throw new IllegalArgumentException("Argument less than min " + Validator.NUMBER_MIN);
		}
		if (!Validator.isLessThanOrEqualToMax(number, Validator.NUMBER_MAX)) {
			throw new IllegalArgumentException("Argument greater than max " + Validator.NUMBER_MAX);
		}

		StringBuilder translation = new StringBuilder();

		if (number == 0) {
			translation.append(MESSAGE_ZERO);
		} else {

			if (number < 0) {
				translation.append(MESSAGE_MINUS + " ");
				number = -number;
			}

			while (number > 0) {
				if (number >= TRILLION) {
					int hundreds = (int) (number / TRILLION);
					bigNumberHundredsAndTens(hundreds, translation, MESSAGE_TRILLION);
					number = number % TRILLION;
				} else if (number >= BILLION) {
					int hundreds = (int) (number / BILLION);
					bigNumberHundredsAndTens(hundreds, translation, MESSAGE_BILLION);
					number = number % BILLION;
				} else if (number >= MILLION) {
					int hundreds = (int) (number / MILLION);
					bigNumberHundredsAndTens(hundreds, translation, MESSAGE_MILLION);
					number = number % MILLION;
				} else if (number >= THOUSAND) {
					int hundreds = (int) (number / THOUSAND);
					bigNumberHundredsAndTens(hundreds, translation, MESSAGE_THOUSAND);
					number = number % THOUSAND;
				} else if (number >= HUNDRED) {
					hundreds((int) number, translation);
					tens((int) (number % 100), translation, true);
					number = 0;
				} else {
					tens((int) number, translation, true);
					number = 0;
				}
			}
		}
		return capitalizeFirst(translation.toString());
	}

	/**
	 * A helper method for numbers greater than or equal to 1000. Used to process
	 * hundredth group. For example 555678, the 555 will be handled by this method.
	 * 
	 * @param a
	 *            number to be changed into words. Between 1 and 999.
	 * @param container
	 *            which holds the number as words
	 * @param the
	 *            group string : Trillion, Billion, Million, Thousand
	 */
	private void bigNumberHundredsAndTens(int hundreds, StringBuilder translation, String numberGroup) {
		hundreds(hundreds, translation);
		tens(hundreds % 100, translation, false);
		translation.append(" " + numberGroup + " ");
	}

	/**
	 * Processes the 6 in the 657
	 * 
	 * @param a
	 *            number to be changed into words. Between 1 and 999.
	 * @param container
	 *            which holds the number as words
	 */
	private void hundreds(int value, StringBuilder translation) {
		if (value / 100 != 0) {
			translation.append(messageNumbers[value / 100] + " " + MESSAGE_HUNDRED + ((value % 100 != 0) ? " " : ""));
		}
	}

	/**
	 * Processes the 57 in 657
	 * 
	 * @param a
	 *            number between 0 to 99
	 * @param container
	 *            which holds the number as words
	 * @param add
	 *            an "and" clause to the translation string
	 */
	private void tens(int value, StringBuilder translation, boolean showAnd) {
		if (value == 0) {
			return;
		}
		if (showAnd && translation.length() != 0) {
			translation.append("and ");
		}
		if (value < 20) {
			translation.append(messageNumbers[value]);
		} else {
			translation.append(messageByTens[value / 10] + ((value % 10 != 0) ? " " : ""));
			translation.append(messageNumbers[value % 10]);
		}
	}

	/**
	 * Capitalize the first letter of a sentence.
	 * 
	 * @param the
	 *            string to be changed
	 * @return a string with the first letter capitalized
	 */
	private String capitalizeFirst(String s) {
		if (s == null) {
			return s;
		}
		if (s.isEmpty()) {
			return s;
		}
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
}
