package com.example.alisovets.complex;

/**
 * @author Alexander Lisovets
 */
public class Complex {

	/*real part */ 
	private double real;
	
	/*imaginary part*/
	private double imaginary;

	public Complex() {
		real = 0;
		imaginary = 0;
	}

	public Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	public double getImaginary() {
		return imaginary;
		
	}

	public void setImaginary(double imaginary) {
		this.imaginary = imaginary;
	}

	public double getReal() {
		return real;
	}

	public void setRe(double real) {
		this.real = real;
	}

	public static Complex add(Complex a, Complex b) {
		return new Complex(a.real + b.real, a.imaginary + b.imaginary);
	}

	public static Complex sub(Complex a, Complex b) {
		return new Complex(a.real - b.real, a.imaginary - b.imaginary);
	}

	public static Complex div(Complex a, Complex b) {
		double re = (a.real * b.real + a.imaginary * b.imaginary) / (b.real * b.real + b.imaginary * b.imaginary);
		double im = (a.imaginary * b.real - a.real * b.imaginary) / (b.real * b.real + b.imaginary * b.imaginary);
		return new Complex(re, im);
	}

	public static Complex mul(Complex a, Complex b) {
		return new Complex(a.real * b.real - a.imaginary * b.imaginary, a.imaginary * b.real + a.real * b.imaginary);

	}

	@Override
	public String toString() {
		return real + "+i(" + imaginary + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Complex)) {
			return false;
		}

		Complex complex = (Complex) obj;
		if (complex.real != real) {
			return false;
		}

		if (complex.imaginary != imaginary) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		long v = Double.doubleToLongBits(real + imaginary);
		return (int) (v ^ (v >>> 32));
	}
}
