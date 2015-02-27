package com.example.alisovets.quicksort;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SorterTest {

	@Test
	public void testQuickSortSize1() {
		int[] array = {1};
		Sorter.quickSort(array, 0, 0);
		assertEquals(1, array[0]);
	}

	@Test
	public void testQuickSortSize2Unsorted() {
		int[] array = {1, 0};
		Sorter.quickSort(array, 0, 1);
		assertTrue(checkSortedArray(array));
	}

	@Test
	public void testQuickSortSize2Sorted() {
		int[] array = {0, 1};
		Sorter.quickSort(array, 0, 1);
		assertTrue(checkSortedArray(array));
	}

	@Test
	public void testQuickSortSize3Unsorted() {
		int[] array = {2, 0, 1};
		Sorter.quickSort(array, 0, 2);
		assertEquals(0, array[0]);
		assertEquals(2, array[2]);
		assertTrue(checkSortedArray(array));
	}

	@Test
	public void testQuickSortSize3Sorted() {
		int[] array = {0, 1, 2};
		Sorter.quickSort(array, 0, 2);
		assertEquals(0, array[0]);
		assertEquals(2, array[2]);
		assertTrue(checkSortedArray(array));
	}

	@Test
	public void testQuickSortSize3ReversSorted() {
		int[] array = {2, 1, 0};
		Sorter.quickSort(array, 0, 2);
		assertEquals(0, array[0]);
		assertEquals(2, array[2]);
		assertTrue(checkSortedArray(array));
	}

	@Test
	public void testQuickSortSize4Unsorted() {
		int[] array = {2, 0, 1, 3};
		Sorter.quickSort(array, 0, 2);
		assertEquals(0, array[0]);
		assertEquals(3, array[3]);
		assertTrue(checkSortedArray(array));
	}

	@Test
	public void testQuickSortSize4Sorted() {
		int[] array = {0, 1, 2, 3};
		Sorter.quickSort(array, 0, 3);
		assertEquals(0, array[0]);
		assertEquals(3, array[3]);
		assertTrue(checkSortedArray(array));
	}

	@Test
	public void testQuickSortSize4ReversSorted() {
		int[] array = {3, 2, 1, 0};
		Sorter.quickSort(array, 0, 3);
		assertEquals(0, array[0]);
		assertEquals(3, array[3]);
		assertTrue(checkSortedArray(array));
	}

	@Test
	public void testQuickSortSize5Unsorted() {
		int[] array = {2, 0, 1, 4, 3};
		Sorter.quickSort(array, 0, 4);
		assertEquals(0, array[0]);
		assertEquals(4, array[4]);
		assertTrue(checkSortedArray(array));
	}

	@Test
	public void testQuickSortSize5Sorted() {
		int[] array = {0, 1, 2, 3, 4};
		Sorter.quickSort(array, 0, 4);
		assertEquals(0, array[0]);
		assertEquals(4, array[4]);
		assertTrue(checkSortedArray(array));
	}

	@Test
	public void testQuickSortSize5ReversSorted() {
		int[] array = {4, 3, 2, 1, 0};
		Sorter.quickSort(array, 0, 4);
		assertEquals(0, array[0]);
		assertEquals(4, array[4]);
		assertTrue(checkSortedArray(array));
	}

	@Test
	public void testQuickSortSize10() {
		int[] array = {3, 6, 1, 10, 2, 2, 3, 5, 4, 7};
	
		Sorter.quickSort(array, 0, 9);
		assertEquals(1, array[0]);
		assertEquals(10, array[9]);
		assertTrue(checkSortedArray(array));
	}

	private boolean checkSortedArray(int[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[i - 1]) {
				return false;
			}
		}
		return true;
	}
}
