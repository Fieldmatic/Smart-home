package com.bsep.smart.home.exception;

public class ForbiddenException extends CustomRuntimeException {
	public ForbiddenException() {
		super(ExceptionKeys.FORBIDDEN);
	}
}
