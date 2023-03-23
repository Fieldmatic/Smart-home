package com.bsep.smart.home.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomRuntimeException extends RuntimeException {
    private ExceptionKeys key;
}
