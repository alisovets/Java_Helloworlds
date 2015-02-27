package com.example.alisovets.vehicle;

import com.example.alisovets.human.Human;

public class Bus<T extends Human> extends Vehicle<T> {

	public Bus(int maxSize) {
		super(maxSize);
	}
}
