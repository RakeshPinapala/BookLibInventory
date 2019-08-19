package com.aryans.library.exceptions;

public class GenericLibraryException extends RuntimeException {

	public GenericLibraryException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenericLibraryException(String message) {
		super(message);
	}

	public GenericLibraryException(Throwable cause) {
		super(cause);
	}

}
