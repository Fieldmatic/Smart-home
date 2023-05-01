package com.bsep.smart.home.exception;

public class InvalidSortUserFieldException extends CustomRuntimeException {
    public InvalidSortUserFieldException() {
        super(ExceptionKeys.INVALID_SORT_USER_FIELD);
    }
}
