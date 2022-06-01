package com.bridgelabz.alamarocaine.exception;

public class PlatNotFoundException extends RuntimeException {

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

	public PlatNotFoundException(String message) {
		this.message = message;

	}
}
