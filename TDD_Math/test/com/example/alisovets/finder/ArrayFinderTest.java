package com.example.alisovets.finder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.alisovets.finder.ArrayFinder;

public class ArrayFinderTest {
	
	@Test(expected= NullPointerException.class)
	public void binarySearch_ArrayIsNull_ThrowsException(){
		ArrayFinder.binarySearch(null, 1);
	}
	
	@Test
	public void binarySearch_ArrayIsEmpty_NothingFind(){
		int[] array = {};
		int index = ArrayFinder.binarySearch(array, 1);
		assertEquals(-1, index);
	}
	
	@Test
	public void binarySearch_ArrayHas11ItemDesiredIsNot_NothingFind(){
		int[] array = {0};
		int index = ArrayFinder.binarySearch(array, 1);
		assertEquals(-1, index);
	}

	@Test
	public void binarySearch_ArrayHas11ItemDesiredIs_Returns0(){
		int[] array = {0};
		int index = ArrayFinder.binarySearch(array, 0);
		assertEquals(0, index);
	}
	
	@Test
	public void binarySearch_ArrayHasEvenNumberOfItem_ReturnsIndex(){
		int[] array = {0, 1, 2, 3, 5, 6};
		int index = ArrayFinder.binarySearch(array, 0);
		assertEquals(0, index);
		index = ArrayFinder.binarySearch(array, 1);
		assertEquals(1, index);
		index = ArrayFinder.binarySearch(array, 2);
		assertEquals(2, index);
		index = ArrayFinder.binarySearch(array, 3);
		assertEquals(3, index);
		index = ArrayFinder.binarySearch(array, 5);
		assertEquals(4, index);
		index = ArrayFinder.binarySearch(array, 6);
		assertEquals(5, index);
	}
	
	@Test
	public void binarySearch_ArrayHasOddNumberOfItem_ReturnsIndex(){
		int[] array = {0, 1, 2, 3, 5};
		int index = ArrayFinder.binarySearch(array, 0);
		assertEquals(0, index);
		index = ArrayFinder.binarySearch(array, 1);
		assertEquals(1, index);
		index = ArrayFinder.binarySearch(array, 2);
		assertEquals(2, index);
		index = ArrayFinder.binarySearch(array, 3);
		assertEquals(3, index);
		index = ArrayFinder.binarySearch(array, 5);
		assertEquals(4, index);
	}
	
	
}
