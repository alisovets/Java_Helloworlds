package com.example.alisovets.mersenne;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.alisovets.mersenne.Mersenne;

public class MersenTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void calculateMersens_Negative_ThrowsException(){
		Mersenne.calculateMersens(-1);
	}
	
	@Test
	public void calculateMersens_LessThenMin_ReturnsEmptyArray(){
		long[] mersenns =  Mersenne.calculateMersens(2L);
		assertEquals(0, mersenns.length);
	}
	
	@Test
	public void calculateMersens_OrdinaryValue_CalculateValues(){
		long[] mersenns =  Mersenne.calculateMersens(1024L);
		assertEquals(4, mersenns.length);
		assertEquals(3, mersenns[0]);
		assertEquals(7, mersenns[1]);
		assertEquals(31, mersenns[2]);
		assertEquals(127, mersenns[3]);
	}
}
