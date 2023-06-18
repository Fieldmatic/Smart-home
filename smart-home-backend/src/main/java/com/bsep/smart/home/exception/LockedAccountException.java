package com.bsep.smart.home.exception;

public class LockedAccountException extends CustomRuntimeException {
    public LockedAccountException() {
        super(ExceptionKeys.LOCKED_ACCOUNT_EXCEPTION);
    }
}
