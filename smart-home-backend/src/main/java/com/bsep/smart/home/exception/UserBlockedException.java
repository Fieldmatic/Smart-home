package com.bsep.smart.home.exception;

public class UserBlockedException extends CustomRuntimeException {
	public UserBlockedException() {
		super(ExceptionKeys.USER_BLOCKED);
	}
}
