package com.iiht.training.certificates.exceptions;

public class CertificateNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CertificateNotFoundException() {

	}

	public CertificateNotFoundException(String message) {
		super(message);
	}
}
