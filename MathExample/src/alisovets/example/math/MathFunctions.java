package alisovets.example.math;

public class MathFunctions {
	private static final double SQRT_EPSILON = 1e-100;
	
	/**
	 * Returns the correctly rounded of a given accuracy positive square root of a double value.
	 * @param a - the number square root is to be returned.
	 * @param epsilon - accuracy  
	 * @return - the square root of x
	 */
	public static double sqrt(double a){
		if(a == 0.0){
			return 0.0;
		}
		
		double xn = a;
		double x = xn;
		
		if(a < 0.0){
			throw new IllegalArgumentException();
		}
		
		do{
			xn = x;
			x = 0.5 * (xn + a / xn);
		}
		
		while((abs(x * x - a) > SQRT_EPSILON) && (x != xn));
		return x ;		
	}

	/**
	 * Returns the long factorial value of a int value
	 * @param x - the number whose factorial is to be returned. x should be between 0 and 20. 
	 * @return - the factorial of x
	 */
	public static double factorial(int x){
		if(x < 0){
			throw new IllegalArgumentException();
		}
	
		if((x == 1) || (x == 0)){
			return 1L;
		}
		return  x * factorial(x - 1);		
	}
	
	/**
	 * Returns the value of the first argument raised to the power of the second argument.
	 * @param a - the base.
	 * @param n - the exponent. 
	 * @return the value of the first argument raised to the power of the second argument.
	 */
	public static double pow(double a, int n){		
		if(n == 0){
			return 1.0;
		}
		
		if(n > 0){
			return a * pow(a, n - 1);			
		}
		else{
			return 1 / a / pow(a, n + 1);
		}
	}
	
	/**
	 * Returns the absolute value of a double value.
	 * @param x - the number whose absolute value is to be returned.  
	 * @return - the absolute value of x
	 */
	public static double abs(double x){
		if(x >= 0){
			return x;
		}
		return -x;
	}
	
	/**
	 * Euler's number e raised to the power of a double value
	 * @param x
	 * @return e raised to the power x
	 */
	public static double exp(double x){
		final double EPSILON = 1e-15;
		double exp = 1;
		double prevValue = 0;
		for(int i =  1; i <= 1000; i++){
			exp += pow(x, i) / factorial(i);
			if(abs(exp - prevValue) < EPSILON){
				break;
			}
			prevValue = exp;
		}
		return exp;
	}
	
	/**
	 * returns Euler's number e raised to the power of a double value
	 * without the use of the functions pow() and factorial()
	 * @param x
	 * @return e raised to the power x
	 */
	public static double quickExp(double x){
		final double EPSILON = 1e-16;
		double exp = 1;
		double k = 1;
		for(int i =  1; i <= 1000; i++){
			k *= x / i; 
			exp += k;
			if(abs(k) < EPSILON){
				break;
			}
		}
		
		return exp;
	}
	
	/**
	 * returns result of function sqrt(exp(x) - |x|)
	 * @param x
	 * @return result of function sqrt(exp(x) - |x|)
	 */
	public static double myFunction(double x){
		return sqrt(quickExp(x) - abs(x));
	}
}
