package com.jwilcox.translator;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidatorTest {

	@Test
	public final void testIsGreaterThanOrEqualToMin() {
		assertTrue(Validator.isGreaterThanOrEqualToMin(101, 100));
		assertTrue(Validator.isGreaterThanOrEqualToMin(100, 100));
		assertFalse(Validator.isGreaterThanOrEqualToMin(90, 100));
	}

	@Test
	public final void testIsLessThanEqualToMax() {
		assertTrue(Validator.isLessThanOrEqualToMax(9, 11));
		assertTrue(Validator.isLessThanOrEqualToMax(11, 11));
		assertFalse(Validator.isLessThanOrEqualToMax(12, 11));
	}

	@Test
	public final void testIsNumber() {
		assertFalse(Validator.isNumber("-"));
		assertFalse(Validator.isNumber("fgdfg sgsf 335"));
		assertFalse(Validator.isNumber("-2352.676"));
		assertTrue(Validator.isNumber("335"));
		assertTrue(Validator.isNumber("-335"));
	}

	@Test
	public final void testIsValid() {
		assertTrue(Validator.isValid("335"));
		assertTrue(Validator.isValid("-335"));
		assertTrue(Validator.isValid(String.valueOf(Validator.NUMBER_MAX)));
		assertTrue(Validator.isValid(String.valueOf(Validator.NUMBER_MIN)));
	}

}
