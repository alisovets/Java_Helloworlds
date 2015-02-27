package com.example.alisovets.vehicle;

import com.example.alisovets.human.Human;

public class Car<T extends Human> extends Vehicle<T> {

	public Car(int maxSize) {
		super(maxSize);
	}

}
