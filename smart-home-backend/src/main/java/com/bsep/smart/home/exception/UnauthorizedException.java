package com.bsep.smart.home.exception;

public class UnauthorizedException extends CustomRuntimeException {
	public UnauthorizedException() {
		super(ExceptionKeys.UNAUTHORIZED);
	}
}
