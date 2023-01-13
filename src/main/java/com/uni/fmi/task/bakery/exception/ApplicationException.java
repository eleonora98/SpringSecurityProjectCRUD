package com.uni.fmi.task.bakery.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = -3733652357442775046L;

	public ApplicationException(String message) {
		super(message);
	}
	
	public ApplicationException(String message, Throwable e) {
		super(message, e);
	}
}

