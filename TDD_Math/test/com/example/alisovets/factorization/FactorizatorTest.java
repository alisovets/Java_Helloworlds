package com.example.alisovets.factorization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.alisovets.factorization.Factorizator;

public class FactorizatorTest {

	@Test(expected = IllegalArgumentException.class)
	public void caculateFactorsAllFactors_NumberIs0_ThrowsException(){
		Factorizator.calculateFactorsAllFactors(0);
	}
	
	@Test
	public void caculateFactorsAllFactors_NumberIsPrime_ReturnsEmptyArray(){
		long number = 5L;
		int expectedLength = Factorizator.calculateFactorsAllFactors(number).length;
		assertEquals(0, expectedLength);
	}
	
	@Test
	public void caculateFactorsAllFactors_NumberHas2PrimeFactor_ReturnsArrayWith2Numbers(){
		long number = 5 * 7 ;
		long[] numbers = Factorizator.calculateFactorsAllFactors(number);
		
		assertEquals(2, numbers.length);
		assertEquals(5, numbers[0]);
		assertEquals(7, numbers[1]);
	}
	
	@Test
	public void caculateFactorsAllFactors_NumberEqualsProductOf2EqualPrimeNumbersAndNotEqual_ReturnsArrayWith2Numbers(){
		long number = 5 * 5 * 7   ;
		long[] numbers = Factorizator.calculateFactorsAllFactors(number);
		assertEquals(2, numbers.length);
		assertEquals(5, numbers[0]);
		assertEquals(7, numbers[1]);
	}
	
	@Test
	public void caculateFactorsAllFactors_NumberEqualsProductOf5PrimeNumbers_ReturnsArrayWith5Numbers(){
		long number = 5 * 7 * 37 * 457 * 2459   ;
		long[] numbers = Factorizator.calculateFactorsAllFactors(number);
		assertEquals(5, numbers.length);
		assertEquals(5, numbers[0]);
		assertEquals(7, numbers[1]);
		assertEquals(37, numbers[2]);
		assertEquals(457, numbers[3]);
		assertEquals(2459, numbers[4]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateFactor_NumberIs0_ThrowsException(){
		Factorizator.calculateFactor(0);	
	}
	
	@Test
	public void calculateFactor_NumberIsNegativePrime_Return1(){
		long expected = 1;
		long factor = Factorizator.calculateFactor(-5);
		assertEquals(expected, factor);
	}
	
	@Test
	public void calculateFactor_NumberIsNegativeNotPrime_CalculateFactor(){
		long expected = 5;
		long factor = Factorizator.calculateFactor(-35);
		assertEquals(expected, factor);
	}
	
	@Test
	public void calculateFactor_NumberIs1_Returns1(){
		long expected = 1;
		long factor = Factorizator.calculateFactor(1);
		assertEquals(expected, factor);
	}
	
	@Test
	public void calculateFactor_NumberIs2_Returns1(){
		long expected = 1;
		long factor = Factorizator.calculateFactor(2);
		assertEquals(expected, factor);
	}
	
	@Test
	public void calculateFactor_NumberIsPrime_Returns1(){
		long expected = 1;
		long factor = Factorizator.calculateFactor(101);
		assertEquals(expected, factor);
	}
	
	@Test
	public void calculateFactor_NumberIsNotPrime_CalculateFactor(){
		long expected = 5;
		long factor = Factorizator.calculateFactor(55);
		assertEquals(expected, factor);
	}
	
	@Test
	public void isPrime_NuberIsLessMinPrime_ReturnsFals(){
		assertFalse(Factorizator.isPrime(1));	
	}
	
	@Test
	public void isPrime_NuberIsMinPrime_ReturnsTrue(){
		assertTrue(Factorizator.isPrime(2));	
	}
	
	@Test
	public void isPrime_NuberIsPrime_ReturnsTrue(){
		assertTrue(Factorizator.isPrime(37));	
	}
	
	@Test
	public void isPrime_NuberIsOdd_ReturnsFals(){
		assertFalse(Factorizator.isPrime(40));	
	}
	
	@Test
	public void isPrime_NuberIsEven_ReturnsFals(){
		assertFalse(Factorizator.isPrime(35));	
	}
	
	
	@Test
	public void lenOfNumber_0_Return0(){
		int expected = 0;
		assertEquals(expected, Factorizator.lengthOfNumber(0));
	}
	
	@Test
	public void lenOfNumber_Negative_CalculateLenght(){
		int expected = 64;
		assertEquals(expected, Factorizator.lengthOfNumber(-10L));
	}
	
	@Test
	public void lenOfNumber_OrdinaryNumber1_CalculateLenght(){
		int expected = 10;
		assertEquals(expected, Factorizator.lengthOfNumber(1023));
	}
	
	@Test
	public void lenOfNumber_OrdinaryNumber2_CalculateLenght(){
		int expected = 11;
		assertEquals(expected, Factorizator.lengthOfNumber(1024));
	}
}
