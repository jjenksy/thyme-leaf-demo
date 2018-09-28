package com.demo.renderview.demo.config.exception;

import org.springframework.security.authentication.AccountStatusException;

public class FirstLoginException extends AccountStatusException {

	public FirstLoginException(String msg) {
		super(msg);
	}

	public FirstLoginException(String msg, Throwable t) {
		super(msg, t);
	}
}
