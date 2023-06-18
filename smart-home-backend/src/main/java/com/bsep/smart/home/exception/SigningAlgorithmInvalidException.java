package com.bsep.smart.home.exception;

public class SigningAlgorithmInvalidException extends CustomRuntimeException {
    public SigningAlgorithmInvalidException() {
        super(ExceptionKeys.SIGNING_ALGORITHM_INVALID);
    }
}
