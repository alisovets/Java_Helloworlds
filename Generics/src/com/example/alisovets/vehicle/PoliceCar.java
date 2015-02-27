package com.example.alisovets.vehicle;

import com.example.alisovets.human.Policeman;

public class PoliceCar<T extends Policeman> extends Car<T> {

	public PoliceCar(int maxSize) {
		super(maxSize);
	}

}
