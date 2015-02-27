package com.example.alisovets.fibonacci;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.alisovets.fibonacci.Fibonacci;


public class FibonacciTest {

	@Test(expected= IllegalArgumentException.class)
	public void fibonacci_ArgumentIsNegative_ThrowsException(){
		Fibonacci.fibonacci(-1);
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void fibonacci_ArgumentIsMoreThenMac_ThrowsException(){
		Fibonacci.fibonacci(Fibonacci.MAX_ARGUMENT + 1);		
	}
	
	@Test
	public void fibonacci_ArgumentIsMinimal_CalculateValue(){
		long expected = 1L;
		long number = Fibonacci.fibonacci(0);
		assertEquals(expected, number);
	}
	
	@Test
	public void fibonacci_ArgumentIsOrdiaryValue_CalculateValue(){
		long expected = 34L;
		long number = Fibonacci.fibonacci(8);
		assertEquals(expected, number);
	}
	
}
