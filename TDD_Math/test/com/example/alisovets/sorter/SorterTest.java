package com.example.alisovets.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.alisovets.sorter.Sorter;

public class SorterTest {

	@Test(expected = NullPointerException.class)
	public void sort_ArrayIsNull_ThrowsException() {
		Sorter.sort(null);
	}

	@Test
	public void sort_ArrayIsEmpty_NoChange() {
		int[] array = new int[0];
		Sorter.sort(array);
	}

	@Test
	public void sort_ArrayHave1Element_NoChange() {
		int[] array = new int[1];
		array[0] = 1;
		Sorter.sort(array);
		assertEquals(1, array[0]);
	}

	@Test
	public void sort_ArrayHave2ElementsNoSorted_SortsArray() {
		int[] array = new int[2];
		array[0] = 1;
		array[1] = 0;
		Sorter.sort(array);
		assertEquals(0, array[0]);
		assertEquals(1, array[1]);
	}

	@Test
	public void sort_ArrayHave2ElementsSorted_NoChange() {
		int[] array = new int[2];
		array[0] = 0;
		array[1] = 1;
		Sorter.sort(array);
		assertEquals(0, array[0]);
		assertEquals(1, array[1]);
	}

	@Test
	public void sort_ArrayHave2EqualElements_NoChange() {
		int[] array = new int[2];
		array[0] = 0;
		array[1] = 0;
		Sorter.sort(array);
		assertEquals(0, array[0]);
		assertEquals(0, array[1]);
	}

	@Test
	public void sort_ArrayHaveSmallOddNumberOfElementsSorted_NoChange() {
		int[] array = new int[3];
		array[0] = 0;
		array[1] = 1;
		array[2] = 2;
		Sorter.sort(array);
		assertEquals(0, array[0]);
		assertEquals(1, array[1]);
		assertEquals(2, array[2]);
	}

	@Test
	public void sort_ArrayHaveSmallOddNumberOfElementsReversSorted_SortsArray() {
		int[] array = new int[3];
		array[0] = 2;
		array[1] = 1;
		array[2] = 0;
		Sorter.sort(array);
		assertEquals(0, array[0]);
		assertEquals(1, array[1]);
		assertEquals(2, array[2]);
	}

	@Test
	public void sort_ArrayHaveSmallOddNumberOfElementsNotSorted_SortsArray() {
		int[] array = new int[3];
		array[0] = 2;
		array[1] = 0;
		array[2] = 1;
		Sorter.sort(array);
		assertEquals(0, array[0]);
		assertEquals(1, array[1]);
		assertEquals(2, array[2]);
	}

	@Test
	public void sort_ArrayHaveSmallOddNumberOfElementsNotSortedHasEqualElemens_SortsArray() {
		int[] array = new int[3];
		array[0] = 2;
		array[1] = 0;
		array[2] = 0;
		Sorter.sort(array);
		assertEquals(0, array[0]);
		assertEquals(0, array[1]);
		assertEquals(2, array[2]);
	}

	@Test
	public void sort_ArrayHaveSmallOddNumberOfElementsAllEquals_NoChange() {
		int[] array = new int[3];
		array[0] = 0;
		array[1] = 0;
		array[2] = 0;
		Sorter.sort(array);
		assertEquals(0, array[0]);
		assertEquals(0, array[1]);
		assertEquals(0, array[2]);
	}

	@Test
	public void sort_ArrayIsNotSorted_SortsArray() {
		int[] array = { 9, 8, 5, 6, 7, 4, 1, 2, 3, 0 };
		Sorter.sort(array);
		assertEquals(0, array[0]);
		assertEquals(1, array[1]);
		assertEquals(2, array[2]);
		assertEquals(3, array[3]);
		assertEquals(4, array[4]);
		assertEquals(5, array[5]);
		assertEquals(6, array[6]);
		assertEquals(7, array[7]);
		assertEquals(8, array[8]);
		assertEquals(9, array[9]);
	}

}
