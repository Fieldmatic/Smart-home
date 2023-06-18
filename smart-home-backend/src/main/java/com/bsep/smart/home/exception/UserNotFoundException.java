package com.bsep.smart.home.exception;

public class UserNotFoundException extends CustomRuntimeException {
	public UserNotFoundException() {
		super(ExceptionKeys.USER_NOT_FOUND);
	}
}
