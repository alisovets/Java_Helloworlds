package com.example.alisovets.vehicle;

import com.example.alisovets.human.Firefighter;

public class FirefighterCar<T extends Firefighter> extends Car<T> {

	public FirefighterCar(int maxSize) {
		super(maxSize);
	}

}
