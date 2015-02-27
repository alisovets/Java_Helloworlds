package com.example.alisovets.vehicle;

public class NotEnoughSeatsException extends RuntimeException {

	private static final long serialVersionUID = 7124254569506319320L;
	private String message;
	
	public NotEnoughSeatsException(){
	}
	
	public NotEnoughSeatsException(String message){
		super(message);
		this.message = message;
	}
	
	public String toString(){
		if(message == null)
			return getClass().toString() + "!";
		return getClass().toString() + "! " + message;
	}
}
