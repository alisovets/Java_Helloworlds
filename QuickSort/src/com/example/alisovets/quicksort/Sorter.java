package com.example.alisovets.quicksort;

import java.util.Arrays;
import java.util.Random;

public class Sorter {
	
	/**
	 * Implements the quickSort algorithm
	 * Sorts the array elements whose indices fall within the specified range  
	 * @param array - an array to sort
	 * @param minIndex
	 * @param maxIndex
	 */
	public static void quickSort(int[] array, int minIndex, int maxIndex) {
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
		for(int n = 5; n < 25; n++){
			Random random = new Random();
			int []array = new int [n];
			for(int i = 0; i < array.length; i++){
				array[i] = random.nextInt(30);
			}
			
			System.out.println("before sort array = " + Arrays.toString(array));
			quickSort(array,  0, array.length-1);
			System.out.println("after  sort array = " + Arrays.toString(array));
			System.out.println();
		}		
	}
}
