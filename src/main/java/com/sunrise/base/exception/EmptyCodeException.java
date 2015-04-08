package com.sunrise.base.exception;

import org.apache.shiro.authc.AuthenticationException;

public class EmptyCodeException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8245779787458085085L;

	public EmptyCodeException() {
		super();
	}

	public EmptyCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyCodeException(String message) {
		super(message);
	}

	public EmptyCodeException(Throwable cause) {
		super(cause);
	}
}
