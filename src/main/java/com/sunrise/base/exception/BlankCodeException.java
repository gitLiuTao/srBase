package com.sunrise.base.exception;

import org.apache.shiro.authc.AuthenticationException;

public class BlankCodeException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8245779787458085085L;

	public BlankCodeException() {
		super();
	}

	public BlankCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BlankCodeException(String message) {
		super(message);
	}

	public BlankCodeException(Throwable cause) {
		super(cause);
	}
}
