package com.aryans.library.exceptions;

public class ResourseNotFoundException extends RuntimeException{

	public ResourseNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourseNotFoundException(String message) {
		super(message);
	}

	public ResourseNotFoundException(Throwable cause) {
		super(cause);
	}

}
