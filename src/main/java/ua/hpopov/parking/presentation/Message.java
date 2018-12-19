package ua.hpopov.parking.presentation;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable{
	private String message="";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
