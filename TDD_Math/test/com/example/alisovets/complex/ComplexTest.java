package com.example.alisovets.complex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.example.alisovets.complex.Complex;

public class ComplexTest {
	private static final double DEFAULT_RE = 1.1;
	private static final double DEFAULT_IM = 2.2;
	private static final double DELTA = 1e-16;
	Complex complex;

	@Before
	public void init() {
		complex = new Complex(DEFAULT_RE, DEFAULT_IM);
	}

	@Test
	public void Complex_NoParameter_ObjectCreated() {
		complex = new Complex();
		assertNotNull(complex);
	}

	@Test
	public void Complex_WithOrdinaryParameters_ObjectCreated() {
		assertNotNull(complex);
	}

	@Test
	public void Complex_WithNaNParameters_ObjectCreated() {
		complex = new Complex(Double.NaN, Double.NaN);
		assertNotNull(complex);
	}

	@Test
	public void getRe_ObjectCreated_ReturnValue() {
		double re = complex.getReal();
		assertEquals(DEFAULT_RE, re, DELTA);
	}

	@Test
	public void getIm_ObjectCreated_ReturnValue() {
		double im = complex.getImaginary();
		assertEquals(DEFAULT_IM, im, DELTA);
	}

	@Test
	public void setRe_OrdinaryValue_ValueIsSet() {
		double expected = 5.5;
		complex.setRe(expected);
		double re = complex.getReal();
		assertEquals(expected, re, DELTA);
	}

	@Test
	public void setRe_NaNValue_ValueIsSet() {
		double expected = Double.NaN;
		complex.setRe(expected);
		double re = complex.getReal();
		assertEquals(expected, re, DELTA);
	}

	@Test
	public void setIm_OrdinaryValue_ValueIsSet() {
		double expected = 5.5;
		complex.setImaginary(expected);
		double im = complex.getImaginary();
		assertEquals(expected, im, DELTA);
	}

	@Test
	public void setIm_NaNValue_ValueIsSet() {
		double expected = Double.NaN;
		complex.setImaginary(expected);
		double im = complex.getImaginary();
		assertEquals(expected, im, DELTA);
	}

	@Test
	public void add_TwoOrdinaryObjects_ReturnNewAppropriateObject() {
		double expectedRe = 4.2;
		double expectedIm = 6.3;
		Complex complex2 = new Complex(3.1, 4.1);
		Complex rezComplex = Complex.add(complex, complex2);
		assertEquals(expectedRe, rezComplex.getReal(), DELTA);
		assertEquals(expectedIm, rezComplex.getImaginary(), DELTA);
	}

	@Test
	public void add_OneObjectHasNaNFields_ReturnNewObjectWithNaNFields() {
		double expected = Double.NaN;
		Complex complex2 = new Complex(expected, expected);
		Complex rezComplex = Complex.add(complex, complex2);
		assertEquals(expected, rezComplex.getReal(), DELTA);
		assertEquals(expected, rezComplex.getImaginary(), DELTA);
	}

	@Test(expected = NullPointerException.class)
	public void add_FirstObjectIsNull_ThrowsException() {
		Complex.add(null, complex);
	}

	@Test(expected = NullPointerException.class)
	public void add_SecondObjectIsNull_ThrowsException() {
		Complex.add(complex, null);
	}

	@Test
	public void sub_TwoOrdinaryObjects_ReturnNewAppropriateObject() {
		double expectedRe = 3.1;
		double expectedIm = 4.1;
		Complex complex2 = new Complex(4.2, 6.3);
		Complex rezComplex = Complex.sub(complex2, complex);
		assertEquals(expectedRe, rezComplex.getReal(), DELTA);
		assertEquals(expectedIm, rezComplex.getImaginary(), DELTA);
	}

	@Test
	public void sub_firstObjectHasNaNFields_ReturnNewObjectWithNaNFields() {
		double expected = Double.NaN;
		Complex complex2 = new Complex(expected, expected);
		Complex rezComplex = Complex.sub(complex2, complex);
		assertEquals(expected, rezComplex.getReal(), DELTA);
		assertEquals(expected, rezComplex.getImaginary(), DELTA);
	}

	@Test
	public void sub_SecondObjectHasNaNFields_ReturnNewObjectWithNaNFields() {
		double expected = Double.NaN;
		Complex complex2 = new Complex(expected, expected);
		Complex rezComplex = Complex.sub(complex, complex2);
		assertEquals(expected, rezComplex.getReal(), DELTA);
		assertEquals(expected, rezComplex.getImaginary(), DELTA);
	}

	@Test(expected = NullPointerException.class)
	public void sub_FirstObjectIsNull_ThrowsException() {
		Complex.sub(null, complex);
	}

	@Test(expected = NullPointerException.class)
	public void sub_SecondObjectIsNull_ThrowsException() {
		Complex.sub(complex, null);
	}

	@Test
	public void mul_TwoOrdinaryObjects_ReturnNewAppropriateObject() {
		double expectedRe = -9.24;
		double expectedIm = 16.17;
		Complex complex2 = new Complex(4.2, 6.3);
		Complex rezComplex = Complex.mul(complex2, complex);
		assertEquals(expectedRe, rezComplex.getReal(), DELTA);
		assertEquals(expectedIm, rezComplex.getImaginary(), DELTA);
	}

	@Test
	public void mul_firstObjectHasNaNFields_ReturnNewObjectWithNaNFields() {
		double expected = Double.NaN;
		Complex complex2 = new Complex(expected, expected);
		Complex rezComplex = Complex.mul(complex2, complex);
		assertEquals(expected, rezComplex.getReal(), DELTA);
		assertEquals(expected, rezComplex.getImaginary(), DELTA);
	}

	@Test
	public void mul_SecondObjectHasNaNFields_ReturnNewObjectWithNaNFields() {
		double expected = Double.NaN;
		Complex complex2 = new Complex(expected, expected);
		Complex rezComplex = Complex.mul(complex, complex2);
		assertEquals(expected, rezComplex.getReal(), DELTA);
		assertEquals(expected, rezComplex.getImaginary(), DELTA);
	}

	@Test(expected = NullPointerException.class)
	public void mul_FirstObjectIsNull_ThrowsException() {
		Complex.mul(null, complex);
	}

	@Test(expected = NullPointerException.class)
	public void mul_SecondObjectIsNull_ThrowsException() {
		Complex.mul(complex, null);
	}

	@Test
	public void div_TwoOrdinaryObjects_ReturnNewAppropriateObject() {
		double expectedRe = 2.109090909090909;
		double expectedIm = -0.4;
		Complex complex2 = new Complex(3.2, 4.2);
		Complex rezComplex = Complex.div(complex2, complex);
		assertEquals(expectedRe, rezComplex.getReal(), DELTA);
		assertEquals(expectedIm, rezComplex.getImaginary(), DELTA);
	}

	@Test
	public void div_firstObjectHasNaNFields_ReturnNewObjectWithNaNFields() {
		double expected = Double.NaN;
		Complex complex2 = new Complex(expected, expected);
		Complex rezComplex = Complex.div(complex2, complex);
		assertEquals(expected, rezComplex.getReal(), DELTA);
		assertEquals(expected, rezComplex.getImaginary(), DELTA);
	}

	@Test
	public void div_SecondObjectHasNaNFields_ReturnNewObjectWithNaNFields() {
		double expected = Double.NaN;
		Complex complex2 = new Complex(expected, expected);
		Complex rezComplex = Complex.div(complex, complex2);
		assertEquals(expected, rezComplex.getReal(), DELTA);
		assertEquals(expected, rezComplex.getImaginary(), DELTA);
	}

	@Test(expected = NullPointerException.class)
	public void div_FirstObjectIsNull_ThrowsException() {
		Complex.div(null, complex);
	}

	@Test(expected = NullPointerException.class)
	public void div_SecondObjectIsNull_ThrowsException() {
		Complex.div(complex, null);
	}

	@Test
	public void equals_twoEqualsObjects_ReturnsTrue() {
		Complex complex2 = new Complex(DEFAULT_RE, DEFAULT_IM);
		assertTrue(complex.equals(complex2));
		assertTrue(complex2.equals(complex));
	}

	@Test
	public void equals_twoNotEqualsRe_ReturnsFalse() {
		Complex complex2 = new Complex(DEFAULT_RE + 1, DEFAULT_IM);
		assertFalse(complex.equals(complex2));
		assertFalse(complex2.equals(complex));
	}

	@Test
	public void equals_twoNotEqualsIm_ReturnsFalse() {
		Complex complex2 = new Complex(DEFAULT_RE, DEFAULT_IM + 1);
		assertFalse(complex.equals(complex2));
		assertFalse(complex2.equals(complex));
	}

	@Test
	public void equals_twoEqualsObjectsWithNaNValue_ReturnsFalse() {
		Complex complex1 = new Complex(DEFAULT_RE, Double.NaN);
		Complex complex2 = new Complex(DEFAULT_RE, Double.NaN);
		assertFalse(complex1.equals(complex2));
		assertFalse(complex2.equals(complex1));
	}

	@Test
	public void equals_ObjectIsNotComplex_ReturnsFalse() {
		Object obj = Integer.valueOf(1);
		assertFalse(complex.equals(obj));
	}

	@Test
	public void equals_Null_ReturnsFalse() {
		assertFalse(complex.equals(null));
	}

	@Test
	public void hashCode_twoEqualsObjects_ReturnEqualsValues() {
		Complex complex2 = new Complex(DEFAULT_RE, DEFAULT_IM);
		assertEquals(complex.hashCode(), complex2.hashCode());
	}

	@Test
	public void toString_OrdinaryValues_ReturnsAppropriateString() {
		assertEquals("1.1+i(2.2)", complex.toString());
	}

	@Test
	public void toString_NaNValues_ReturnsAppropriateString() {
		assertEquals("NaN+i(NaN)", new Complex(Double.NaN, Double.NaN).toString());
	}

}
