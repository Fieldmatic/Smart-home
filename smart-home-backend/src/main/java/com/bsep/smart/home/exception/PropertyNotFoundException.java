package com.bsep.smart.home.exception;

public class PropertyNotFoundException extends CustomRuntimeException {
    public PropertyNotFoundException() {
        super(ExceptionKeys.PROPERTY_NOT_FOUND);
    }
}
