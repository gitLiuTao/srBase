package com.sunrise.base.exception;

import org.apache.shiro.authc.AuthenticationException;

public class SendSMSException extends AuthenticationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8245779787458085085L;

	public SendSMSException() {
		super();
	}

	public SendSMSException(String message, Throwable cause) {
		super(message, cause);
	}

	public SendSMSException(String message) {
		super(message);
	}

	public SendSMSException(Throwable cause) {
		super(cause);
	}
}
