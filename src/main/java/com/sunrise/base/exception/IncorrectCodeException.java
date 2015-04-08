package com.sunrise.base.exception;

import org.apache.shiro.authc.AuthenticationException;

public class IncorrectCodeException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8245779787458085085L;

	public IncorrectCodeException() {
		super();
	}

	public IncorrectCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectCodeException(String message) {
		super(message);
	}

	public IncorrectCodeException(Throwable cause) {
		super(cause);
	}
}
