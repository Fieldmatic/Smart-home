package com.bsep.smart.home.exception;

public class InvalidPinException extends CustomRuntimeException {
    public InvalidPinException() {
        super(ExceptionKeys.INVALID_PIN);
    }
}
