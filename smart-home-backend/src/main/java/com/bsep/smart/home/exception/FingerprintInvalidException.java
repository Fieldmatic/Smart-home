package com.bsep.smart.home.exception;

public class FingerprintInvalidException extends CustomRuntimeException {
    public FingerprintInvalidException() {
        super(ExceptionKeys.FINGERPRINT_INVALID);
    }
}
