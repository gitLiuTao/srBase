package com.sunrise.base.exception;

import org.apache.shiro.authc.AuthenticationException;

public class InvalidUserException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8245779787458085085L;

	public InvalidUserException() {
		super();
	}

	public InvalidUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidUserException(String message) {
		super(message);
	}

	public InvalidUserException(Throwable cause) {
		super(cause);
	}
}
