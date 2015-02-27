package com.example.alisovets.vehicle;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle<T>{
	private int maxSize;
	private List<T> passengers;
	
	public Vehicle(int maxSize){
		this.maxSize  = maxSize;
		passengers = new ArrayList<T>(maxSize);
	}
	
	public int getSize(){
		return passengers.size();
	}
	
	public int getMaxSize(){
		return maxSize;
	}
	
	public void load(T passenger){
		if(passengers.size() ==  maxSize){
			throw new NotEnoughSeatsException();
		}
		passengers.add(passenger);
	}
	
	
	public void unload(T passenger){
		if(!passengers.remove(passenger)){
			throw new PassengerNotFoundException();
		}	
	}
	
	
	
}
