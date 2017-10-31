package com.jwilcox.translator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConvertImplTest {
	private ConvertImpl convert;

	@Before
	public void setUp() {
		convert = new ConvertImpl();
	}

	@After
	public void tearDown() {
		convert = null;
	}

	@Test
	public void trillionTest() {
		assertEquals(
				"One trillion three hundred forty two billion three hundred fifty six million seven hundred eighty four thousand three hundred and seventy one",
				convert.convert(1342356784371L));
	}

	@Test
	public void billionTest() {
		assertEquals("Three hundred forty billion fifty six million and seventy one",
				convert.convert(340056000071L));
	}

	@Test
	public void millionTest() {
		assertEquals("Fifty six million and seventy one", convert.convert(56000071L));
		assertEquals("Eight hundred fifty six million and seventy one", convert.convert(856000071L));
		assertEquals(
				"Eight hundred fifty six million four hundred fifty six thousand seven hundred and seventy one",
				convert.convert(856456771L));
	}

	@Test
	public void thousandTest() {
		assertEquals("One thousand three hundred and seventy one", convert.convert(1371L));
		assertEquals("Twenty one thousand three hundred and seventy one", convert.convert(21371L));
		assertEquals("Six hundred twenty one thousand three hundred and seventy one", convert.convert(621371L));
	}

	@Test
	public void hundredTest() {
		assertEquals("Five hundred and seventy one", convert.convert(571L));
		assertEquals("Five hundred", convert.convert(500L));
		assertEquals("Five hundred and one", convert.convert(501L));
	}

	@Test
	public void tensTest() {
		assertEquals("Thirty one", convert.convert(31L));
	}

	@Test
	public void zeroTest() {
		assertEquals("Zero", convert.convert(0L));
	}

	@Test
	public void minusTest() {
		assertEquals("Minus sixty thousand and seventy one", convert.convert(-60071L));
	}
}
