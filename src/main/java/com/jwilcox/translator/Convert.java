package com.jwilcox.translator;

/**
 * Convert numbers into words
 * @author Jim Wilcox
 *
 */
public interface Convert {
	/**
	 * Convert method
	 * @param number to be converted
	 * @return a number as words
	 */
	String convert(long number);
}
