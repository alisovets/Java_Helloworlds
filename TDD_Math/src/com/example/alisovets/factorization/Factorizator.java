package com.example.alisovets.factorization;

import java.util.Arrays;
import java.util.TreeSet;

public class Factorizator {
	/**
	 * calculates an returns as array of Long all prime factors of the number
	 * 
	 * @param number
	 * @return a array of prime factors of the number
	 */
	public static long[] calculateFactorsAllFactors(long number) {
		TreeSet<Long> set = new TreeSet<Long>();
		TreeSet<Long> results = new TreeSet<Long>();
		set.add(number);

		while (!set.isEmpty()) {
			long num = set.pollFirst();
			long factor = calculateFactor(num);

			if (factor == 1) {
				if (num != number) {
					results.add(num);
				}
				continue;
			}
			if (!results.contains(factor)) {
				set.add(factor);
			}

			factor = num / factor;
			if (!results.contains(factor)) {
				set.add(factor);
			}
		}
		long[] factors = new long[results.size()];
		int i = 0;
		for (long factor : results) {
			factors[i++] = factor;
		}
		return factors;
	}

	/**
	 * calculates and returns lengths of the binary representation of the number
	 * 
	 * @param num
	 * @return lengths of the binary representation of the number
	 */
	public static int lengthOfNumber(long num) {
		if (num < 0) {
			return 64;
		}

		if (num == 0) {
			return 0;
		}
		return 1 + (int) (Math.log(num) / Math.log(2.0));
	}

	/**
	 * check if the number passed to the parameter is prime number
	 * 
	 * @param num
	 * @return true if the number is a prime number
	 */
	public static boolean isPrime(long number) {
		if (number < 2) {
			return false;
		}
		return calculateFactor(number) == 1;
	}

	/**
	 * returns one factor of the number passed to the parameter or 1 if number
	 * is prime
	 * 
	 * @param number
	 * @return the minimal factor of the number passed to the parameter or 1 if
	 *         number is prime
	 */
	public static long calculateFactor(long number) {
		if (number == 0) {
			throw new IllegalArgumentException("the number should not be 0.");
		}
		if (number < 0) {
			return calculateFactor(-number);
		}
		if (number == 2) {
			return 1;
		}

		if ((number & 1L) == 0L) {
			return 2L;
		}

		int len = lengthOfNumber(number);

		long factor1 = calculateFactor(number, 1L, 1L, 1L, len, 1);
		long factor2 = number / factor1;
		if (factor1 > factor2) {
			return factor2;
		}
		return factor1;
	}

	/*
	 * returns a number other than 1 that is factor of the number passed to the
	 * first parameter or 1 if number is prime/ parameters 2-6 use parameters
	 * used in the recursive call, and should be equal to 1 at the beginning.
	 */
	private static long calculateFactor(long number, long factor1, long factor2, long product, int numberLength, int rate) {
		if (product > number) {
			return 1L;
		}

		long summand = 1L << rate;

		long mask = summand - 1L;
		if ((product & mask) != (number & mask)) {
			return 1L;
		}

		if (rate * 2 + 1 > numberLength) {
			long f = number / factor2;
			if (f * factor2 == number) {
				return factor2;
			}
			f = number / factor1;
			if (f * factor1 == number) {
				return factor1;
			}
			return 1;

		}

		long rezult = calculateFactor(number, factor1, factor2, product, numberLength, rate + 1);
		if (rezult > 1L) {
			return rezult;
		}

		rezult = calculateFactor(number, factor1 + summand, factor2, product + (factor2 << rate), numberLength, rate + 1);
		if (rezult > 1L) {
			return rezult;
		}

		long newProduct = product + (factor1 << rate);
		long newFactor2 = factor2 + summand;
		rezult = calculateFactor(number, factor1, newFactor2, newProduct, numberLength, rate + 1);
		if (rezult > 1L) {
			return rezult;
		}

		newProduct = newProduct + (newFactor2 << rate);
		return calculateFactor(number, factor1 + summand, newFactor2, newProduct, numberLength, rate + 1);
	}

	
	public static void main(String[] args) {
		
		long number =  124309L * 553627L * 3408877L * 11L; 
		long[] factors = calculateFactorsAllFactors(number);
		System.out.println("factors of number " + number + ": " + Arrays.toString(factors)) ;
		
		number = 100189L * 1005049L * 3480877L;
		factors = calculateFactorsAllFactors(number);
		System.out.println("factors of number " + number + ": " + Arrays.toString(factors)) ;
	}

	
	
	
}
