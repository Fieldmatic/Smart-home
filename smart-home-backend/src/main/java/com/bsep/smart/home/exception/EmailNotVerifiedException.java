package com.bsep.smart.home.exception;

public class EmailNotVerifiedException extends CustomRuntimeException {
    public EmailNotVerifiedException() {
        super(ExceptionKeys.EMAIL_NOT_VERIFIED);
    }
}
