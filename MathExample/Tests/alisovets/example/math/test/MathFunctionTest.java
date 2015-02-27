package alisovets.example.math.test;

import org.junit.Test;

import alisovets.example.math.MathFunctions;
import static org.junit.Assert.assertEquals;

public class MathFunctionTest {
	private static final double SQRT_EPSILON 		= Double.MIN_NORMAL;
	private static final double FACTORIAL_EPSILON 	= 1E-10;
	private static final double POW_EPSILON 		= 1E-12;
	private static final double EXP_EPSILON			= 1E-15;
	
	@Test(expected = IllegalArgumentException.class)
	public void sqrt_NegetiveValue_Exception() {
		MathFunctions.sqrt(-1.0);
	}
	
	@Test
	public void sqrt_ZeroValue_CorrectResult() {
		final double EXPECTED_RESULT = 0.0;
		assertEquals(EXPECTED_RESULT, MathFunctions.sqrt(0.0), SQRT_EPSILON);
	}
	
	@Test
	public void sqrt_IntegerValue1_CorrectResult() {
		final double EXPECTED_RESULT = 1.0;
		assertEquals(EXPECTED_RESULT, MathFunctions.sqrt(1.0), SQRT_EPSILON);
	}
	
	@Test
	public void sqrt_IntegerValue4_CorrectResult() {
		final double EXPECTED_RESULT = 2.0;
		assertEquals(EXPECTED_RESULT, MathFunctions.sqrt(4.0), SQRT_EPSILON);
	}
	
	@Test
	public void sqrt_IntegerValue9_CorrectResult() {
		final double EXPECTED_RESULT = 3.0;
		assertEquals(EXPECTED_RESULT, MathFunctions.sqrt(9), SQRT_EPSILON);
	}
	
	@Test
	public void sqrt_NotIntegerValue_CorrectResult() {
		final double EXPECTED_RESULT = 5.123456789;
		double value = EXPECTED_RESULT * EXPECTED_RESULT;
		assertEquals(EXPECTED_RESULT, MathFunctions.sqrt(value), SQRT_EPSILON);
	}
	
	@Test
	public void sqrt_BigValue_CorrectResult() {
		final double EXPECTED_RESULT = 1.34E154;
		double value = EXPECTED_RESULT * EXPECTED_RESULT;
		assertEquals(EXPECTED_RESULT, MathFunctions.sqrt(value), SQRT_EPSILON);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void factorial_NegativeValue_Exception() {
		MathFunctions.factorial(-1);
	}
	
	@Test
	public void factorial_ZerroValue_CorrectResult() {
		final double EXPECTED_RESULT = 1.0;
		assertEquals(EXPECTED_RESULT, MathFunctions.factorial(0), FACTORIAL_EPSILON);
	}
	
	@Test
	public void factorial_Value1_CorrectResult() {
		final double EXPECTED_RESULT = 1.0;
		assertEquals(EXPECTED_RESULT, MathFunctions.factorial(1), FACTORIAL_EPSILON);
	}
	
	@Test
	public void factorial_Value2_CorrectResult() {
		final double EXPECTED_RESULT = 2.0;
		assertEquals(EXPECTED_RESULT, MathFunctions.factorial(2), FACTORIAL_EPSILON);
	}
	
	@Test
	public void factorial_Value5_CorrectResult() {
		final double EXPECTED_RESULT = 120.0;
		assertEquals(EXPECTED_RESULT, MathFunctions.factorial(5), FACTORIAL_EPSILON);
	}

	@Test
	public void factorial_Value20_CorrectResult() {
		final double EXPECTED_RESULT = 2432902008176640000L;
		assertEquals(EXPECTED_RESULT, MathFunctions.factorial(20), FACTORIAL_EPSILON);
	}
	
	@Test
	public void pow_ExponentIsNegative_CorrectResult(){
		final double EXPECTED_RESULT = 0.47619047619047616;
		assertEquals(EXPECTED_RESULT, MathFunctions.pow(2.1, -1), POW_EPSILON);
	}
	
	@Test
	public void pow_ExponentIsZero_CorrectResult(){
		final double EXPECTED_RESULT = 1.0;
		assertEquals(EXPECTED_RESULT, MathFunctions.pow(2.1, 0), POW_EPSILON);
	}
	
	@Test
	public void pow_ExponentIsOne_CorrectResult(){
		final double EXPECTED_RESULT = 2.1;
		assertEquals(EXPECTED_RESULT, MathFunctions.pow(2.1, 1), POW_EPSILON);
	}
	
	@Test
	public void pow_ExponentIsThree_CorrectResult(){
		final double EXPECTED_RESULT = 10.648;
		assertEquals(EXPECTED_RESULT, MathFunctions.pow(2.2, 3), POW_EPSILON);
	}
	
	@Test
	public void pow_NumberIsZero_CorrectResult(){
		final double EXPECTED_RESULT = 0.0;
		assertEquals(EXPECTED_RESULT, MathFunctions.pow(0, 3), POW_EPSILON);
	}
	
	@Test
	public void pow_NumberIsNegative_CorrectResult(){
		final double EXPECTED_RESULT = -10.648;
		assertEquals(EXPECTED_RESULT, MathFunctions.pow(-2.2, 3), POW_EPSILON);
	}
	
	@Test
	public void pow_NumberIs2ExponentIs63_CorrectResult(){
		final double EXPECTED_RESULT = (double)0x7fffffffffffffffL;
		assertEquals(EXPECTED_RESULT, MathFunctions.pow(2.0, 63), POW_EPSILON);
	}
	
	@Test
	public void exp_ValueIs0_CorrectResult(){
		final double EXPECTED_RESULT = 1.0;
		assertEquals(EXPECTED_RESULT, MathFunctions.exp(0), EXP_EPSILON);
	}
	
	@Test
	public void exp_ValueIs1_CorrectResult(){
		final double EXPECTED_RESULT = 2.718281828459045;
		assertEquals(EXPECTED_RESULT, MathFunctions.exp(1.0), EXP_EPSILON);
	}
	
	@Test
	public void exp_ValueIs2_CorrectResult(){
		final double EXPECTED_RESULT = 7.38905609893065;
		final double EPSILON = 1E-14;
		assertEquals(EXPECTED_RESULT, MathFunctions.exp(2.0), EPSILON);
	}
	
	@Test
	public void exp_ValueIs10_CorrectResult(){
		final double EXPECTED_RESULT = 22026.465794806718;
		final double EPSILON = 1E-10;
		assertEquals(EXPECTED_RESULT, MathFunctions.exp(10.0), EPSILON);
	}
	
	@Test
	public void exp_ValueIs2_2_CorrectResult(){
		final double EXPECTED_RESULT = 9.025013499434122;
		final double EPSILON = 1E-14;
		assertEquals(EXPECTED_RESULT, MathFunctions.exp(2.2), EPSILON);
	}
	
	@Test
	public void quickExp_ValueIS2_2_CorrectResult(){
		final double EXPECTED_RESULT = 9.025013499434122;
		final double EPSILON = 1E-14;
		assertEquals(EXPECTED_RESULT, MathFunctions.quickExp(2.2), EPSILON);
	}
	
	@Test
	public void quickExp_ValueIs5_CorrectResult(){
		final double EXPECTED_RESULT = 148.4131591025766;
		final double EPSILON = 1E-14;
		assertEquals(EXPECTED_RESULT, MathFunctions.quickExp(5.0), EPSILON);
	}
	
	@Test
	public void quickExp_ValueIs10_CorrectResult(){
		final double EXPECTED_RESULT = 22026.465794806718;
		final double EPSILON = 1E-10;
		assertEquals(EXPECTED_RESULT, MathFunctions.quickExp(10.0), EPSILON);
	}
	
	@Test
	public void quickExp_ValueIs100_CorrectResult(){
		final double EXPECTED_RESULT = 2.6881171418161356e43;
		final double EPSILON = 1E29;
		assertEquals(EXPECTED_RESULT, MathFunctions.quickExp(100.0), EPSILON);
	}
	
	@Test
	public void myFunction_ValueIs1_CorrectResult(){
		final double EXPECTED_RESULT = 1.3108324944320862;
		final double EPSILON = 1e-14;
		assertEquals(EXPECTED_RESULT, MathFunctions.myFunction(1), EPSILON);
	}
	
	@Test
	public void myFunction_ValueIs10_CorrectResult(){
		final double EXPECTED_RESULT = 148.37946554293393;
		final double EPSILON = 1e-14;
		assertEquals(EXPECTED_RESULT, MathFunctions.myFunction(10), EPSILON);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void myFunction_ValueIsMinus1_Exception(){
		MathFunctions.myFunction(-1.0);
	}
	
	@Test
	public void myFunction_ValueIsMinus05_Exception(){
		final double EXPECTED_RESULT = 0.3263903486818099;
		final double EPSILON = 1e-14;
		assertEquals(EXPECTED_RESULT, MathFunctions.myFunction(-0.5), EPSILON);
	}
}
