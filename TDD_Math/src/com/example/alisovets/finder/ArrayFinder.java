package com.example.alisovets.finder;

public class ArrayFinder {
	private static int NOT_FOUND = -1;

	/**
	 * Searches the specified array of bytes for the specified value using the
	 * binary search algorithm. The array must be sorted.
	 * 
	 * @param array
	 *            -the array to be searched
	 * @param value
	 *            - the value to be searched for
	 * @return index of the search key, if it is contained in the array;
	 *         otherwise -1;
	 */
	public static int binarySearch(int[] array, int value) {
		if (array.length == 0) {
			return NOT_FOUND;
		}
		return binarySearch(array, value, 0, array.length - 1);
	}

	public static int binarySearch(int[] array, int value, int minIndex, int maxIndex) {
		if (array[minIndex] == value) {
			return minIndex;
		} else if (array[maxIndex] == value) {
			return maxIndex;
		}

		if (maxIndex -  minIndex < 2) {
			return NOT_FOUND;
		}

		int extremeIndex = (minIndex + maxIndex) / 2;
		if (array[extremeIndex] > value) {
			maxIndex = extremeIndex;
		} else {
			minIndex = extremeIndex;
		}

		return binarySearch(array, value, minIndex, maxIndex);
	}

}
