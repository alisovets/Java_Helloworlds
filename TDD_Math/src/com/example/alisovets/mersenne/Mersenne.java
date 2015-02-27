package com.example.alisovets.mersenne;

import java.util.Arrays;

import com.example.alisovets.factorization.Factorizator;

public class Mersenne {
	
	/**
	 * Calculates and returns Mersenne prime numbers that is not more 
	 * than the specified maximum value  
	 * @param maxNumber maximum value
	 * @return an array of the calculated numbers 
	 */
	public static long[] calculateMersens(long maxNumber) {
		if (maxNumber < 0) {
			throw new IllegalArgumentException("max number should be more then 0");
		}

		int len = Factorizator.lengthOfNumber(maxNumber);
		long[] marsens = new long[len];
		int count = 0;
		for (int p = 1; p <= len; p += 1) {
			if (!Factorizator.isPrime(p)) {
				continue;
			}

			long candidat = (1L << p) - 1L;

			if ((candidat > maxNumber) || !Factorizator.isPrime(candidat)) {
				continue;
			}
			marsens[count++] = candidat;

		}
		return Arrays.copyOf(marsens, count);
	}

	public static void main(String[] args) {
		long[] mersenns = calculateMersens(100000000000000000L);
		System.out.println(Arrays.toString(mersenns));

	}

}
