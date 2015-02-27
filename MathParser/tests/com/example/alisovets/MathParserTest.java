package com.example.alisovets;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

import com.example.alisovets.MathParser;

public class MathParserTest {
	private final static double EPSILON = 1e-12;

	@Test
	public void testParser1() throws Exception {
		MathParser parser = new MathParser("  (123.6)");
		Double res = parser.parse();
		assertEquals(123.6, res, EPSILON);
	}

	@Test
	public void testParser2() throws Exception {
		MathParser parser = new MathParser("  (  125)");
		Double res = parser.parse();
		assertEquals(125.0, res, EPSILON);
	}

	@Test
	public void testParser3() throws Exception {
		MathParser parser = new MathParser("  0.6");
		Double res = parser.parse();
		assertEquals(0.6, res, EPSILON);
	}

	@Test
	public void testParser4() throws Exception {
		MathParser parser = new MathParser(" .6");
		Double res = parser.parse();
		assertEquals(0.6, res, EPSILON);
	}

	@Test(expected = ParseException.class)
	public void testParser8() throws Exception {
		MathParser parser = new MathParser(" )");
		parser.parse();
	}

	@Test
	public void testParserPluss1() throws Exception {
		MathParser parser = new MathParser("(111 + 65.3 )");
		Double res = parser.parse();
		assertEquals(176.3, res, EPSILON);
	}

	@Test
	public void testParserPluss2() throws Exception {
		MathParser parser = new MathParser("  (11 +(22   + 33) )");
		Double res = parser.parse();
		assertEquals(66.0, res, EPSILON);
	}

	@Test
	public void testParserMinus1() throws Exception {
		MathParser parser = new MathParser("333.3 -  222 ");
		Double res = parser.parse();
		assertEquals(111.3, res, EPSILON);
	}

	@Test
	public void testParserMinus2() throws Exception {
		MathParser parser = new MathParser("333.3 -  (222 - 111) ");
		Double res = parser.parse();
		assertEquals(222.3, res, EPSILON);
	}

	@Test
	public void testParserPlusMinus() throws Exception {
		MathParser parser = new MathParser(
				"333.3  +  (222 - 122) - (145 - 45 + 100) ");
		Double res = parser.parse();
		assertEquals(233.3, res, EPSILON);
	}

	@Test
	public void testParserMultiply() throws Exception {
		MathParser parser = new MathParser("11*2");
		Double res = parser.parse();
		assertEquals(22.0, res, EPSILON);
	}

	@Test
	public void testParserMultiply2() throws Exception {
		MathParser parser = new MathParser("11*2 * .5");
		Double res = parser.parse();
		assertEquals(11.0, res, EPSILON);
	}

	@Test
	public void testParserArithmetic() throws Exception {
		MathParser parser = new MathParser("125/2+ 1  -  3 *(25 - 2) - 65 /  4");
		Double res = parser.parse();
		assertEquals(-21.75, res, EPSILON);
	}

	@Test
	public void testParserParentheses1() throws Exception {
		MathParser parser = new MathParser("(123)");
		Double res = parser.parse();
		assertEquals(123.0, res, EPSILON);
	}

	@Test
	public void testParserParentheses2() throws Exception {
		MathParser parser = new MathParser("(  (123) )");
		Double res = parser.parse();
		assertEquals(123.0, res, EPSILON);
	}

	@Test
	public void testParserParentheses3() throws Exception {
		MathParser parser = new MathParser("(((123)   )        )        ");
		Double res = parser.parse();
		assertEquals(123.0, res, EPSILON);
	}

	@Test
	public void testParserTrigonometry1() throws Exception {
		MathParser parser = new MathParser("sin(0.53)");
		Double res = parser.parse();
		assertEquals(0.5, res, 0.01);
	}

	@Test
	public void testParserTrigonometry2() throws Exception {
		MathParser parser = new MathParser("asin(sin(0.6))");
		Double res = parser.parse();
		assertEquals(0.6, res, 0.000001);
	}

	@Test
	public void testParserTrigonometry3() throws Exception {
		MathParser parser = new MathParser(
				"asin(sin(0.5)) * 2 - acos(cos(0.5))");
		Double res = parser.parse();
		assertEquals(0.5, res, EPSILON);
	}

	@Test
	public void testParserTrigonometry4() throws Exception {
		MathParser parser = new MathParser("acos(cos(0.5)) * tan(3 - 2.5)");
		Double res = parser.parse();
		assertEquals(Math.acos(Math.cos(0.5)) * Math.tan(0.5), res, EPSILON);
	}

	@Test(expected = ParseException.class)
	public void testParserBadSyntacs() throws Exception {
		MathParser parser = new MathParser("15 * + /  0");
		parser.parse();
	}

}
