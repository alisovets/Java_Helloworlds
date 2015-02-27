package com.example.alisovets.fibonacci;
public class Fibonacci {
	public static int MAX_ARGUMENT = 92;
	/**
	 * calculates and returns the specified term of Fibonacci
	 * @param number the number of the term 
	 * @return the specified term of Fibonacci
	 */
	public static long fibonacci(int number) {
		
		if(number < 0){
			throw new IllegalArgumentException("argument should not be less then 0");
		}
		
		if(number > MAX_ARGUMENT){
			throw new IllegalArgumentException("argument should not be more then " + MAX_ARGUMENT);
		}
		
		if (number < 2) {
			return 1L;
		}
		return fibonacci(number-1) + fibonacci(number-2);
	}

	
	public static void main(String[] args){
		for(int i = 0; i < 40; i++ ){
			System.out.print(fibonacci(i)+ " ");
		}
		System.out.println();
	}
}
