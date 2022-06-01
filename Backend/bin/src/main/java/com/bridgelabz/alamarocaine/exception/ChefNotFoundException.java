package com.bridgelabz.alamarocaine.exception;

public class ChefNotFoundException extends RuntimeException {

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

	public ChefNotFoundException(String message) {
		this.message = message;

	}
}
