package com.bsep.smart.home.exception;

public class PasswordSameException extends CustomRuntimeException {
    public PasswordSameException() {
        super(ExceptionKeys.PASSWORD_SAME);
    }
}
