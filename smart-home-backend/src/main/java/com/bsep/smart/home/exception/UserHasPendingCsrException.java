package com.bsep.smart.home.exception;

public class UserHasPendingCsrException extends CustomRuntimeException {
    public UserHasPendingCsrException() {
        super(ExceptionKeys.USER_HAS_PENDING_CSR);
    }
}
