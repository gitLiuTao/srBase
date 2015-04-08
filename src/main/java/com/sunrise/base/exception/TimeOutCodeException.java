package com.sunrise.base.exception;

import org.apache.shiro.authc.AuthenticationException;

public class TimeOutCodeException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8245779787458085085L;

	public TimeOutCodeException() {
		super();
	}

	public TimeOutCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TimeOutCodeException(String message) {
		super(message);
	}

	public TimeOutCodeException(Throwable cause) {
		super(cause);
	}
}
