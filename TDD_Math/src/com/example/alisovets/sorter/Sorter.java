package com.example.alisovets.sorter;

import java.util.Arrays;
import java.util.Random;

public class Sorter {
	/**
	 * sorts in order value Ascending the passed array.   
	 * @param array for sorted, changes.
	 * @return sorted array.
	 */
	public static int[] sort(int[] array) {
		if(array.length < 2){
			return array;
		}
		int minIndex = 0;
		int maxIndex = array.length - 1;
		quickSort(array, minIndex, maxIndex);
		return array;
	}

	
	private static void quickSort(int[] array, int minIndex, int maxIndex) {
		int leftIndex = minIndex;
		int rightIndex = maxIndex;
		int pivot = (array[leftIndex] + array[rightIndex]) / 2;
		while (leftIndex <= rightIndex) {
			while (array[leftIndex] < pivot){
				leftIndex++;
			}
			
			while (array[rightIndex] > pivot){
				rightIndex--;
			}
			
			if (leftIndex <= rightIndex) {
				int tmp = array[leftIndex];
				array[leftIndex] = array[rightIndex];
				array[rightIndex] = tmp;
				leftIndex++;
				rightIndex--;
			}		
		}
		if(rightIndex > minIndex){
			quickSort(array, minIndex, rightIndex);
		}
		if(leftIndex < maxIndex){
			quickSort(array, leftIndex, maxIndex);
		}

	}
	
	public static void main(String[] args){
		Random random = new Random();
		int []array = new int [25];
		for(int i = 0; i < array.length; i++){
			array[i] = random.nextInt(30);
		}
		System.out.println("before sort array = " + Arrays.toString(array));
		sort(array);
		System.out.println("after  sort array = " + Arrays.toString(array));
		
		array = new int [26];
		for(int i = 0; i < array.length; i++){
			array[i] = random.nextInt(30);
		}
		System.out.println("before sort array = " + Arrays.toString(array));
		sort(array);
		System.out.println("after  sort array = " + Arrays.toString(array));
		
	}
}
