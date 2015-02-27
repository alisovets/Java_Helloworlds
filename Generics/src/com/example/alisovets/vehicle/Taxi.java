package com.example.alisovets.vehicle;

import com.example.alisovets.human.Human;

public class Taxi<T extends Human> extends Car<T> {

	public Taxi(int maxSize) {
		super(maxSize);
	}

}
