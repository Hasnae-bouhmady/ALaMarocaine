package com.bridgelabz.alamarocaine.exception;

public class ChefAlreadyExist extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ChefAlreadyExist(String message) {
		this.message = message;

	}

}
