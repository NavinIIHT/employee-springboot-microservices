package com.iiht.training.certificates.exceptions;

public class InvalidDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidDataException() {

	}

	public InvalidDataException(String message) {
		super(message);
	}
}
