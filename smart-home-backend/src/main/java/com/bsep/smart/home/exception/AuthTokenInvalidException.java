package com.bsep.smart.home.exception;

public class AuthTokenInvalidException extends CustomRuntimeException {
    public AuthTokenInvalidException() {
        super(ExceptionKeys.AUTH_TOKEN_INVALID);
    }
}
