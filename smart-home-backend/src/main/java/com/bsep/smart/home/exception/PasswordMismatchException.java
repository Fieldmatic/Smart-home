package com.bsep.smart.home.exception;

public class PasswordMismatchException extends CustomRuntimeException {
    public PasswordMismatchException() {
        super(ExceptionKeys.PASSWORD_MISMATCH);
    }
}
