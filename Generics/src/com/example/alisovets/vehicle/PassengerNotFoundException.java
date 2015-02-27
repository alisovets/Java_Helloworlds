package com.example.alisovets.vehicle;

public class PassengerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -7009835454111737834L;
	private String message;
	
	public PassengerNotFoundException(){
	}
	
	public PassengerNotFoundException(String message){
		super(message);
		this.message = message;
	}
	
	public String toString(){
		if(message == null)
			return getClass().toString() + "!";
		return getClass().toString() + "! " + message;
	}
}
