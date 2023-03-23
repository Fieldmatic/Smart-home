package com.bsep.smart.home.exception;

public class UserAlreadyExistsException extends CustomRuntimeException {
	public UserAlreadyExistsException() {
		super(ExceptionKeys.USER_ALREADY_EXISTS);
	}
}
