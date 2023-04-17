package com.bsep.smart.home.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;

public class MyValidator implements ConstraintValidator<CommonPasswordValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isValid;
        try {
            isValid = ValidationFunctions.validateCommonlyUsedPasswords(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!isValid) {
            context.buildConstraintViolationWithTemplate("Validation failed").addConstraintViolation();
        }

        return isValid;
    }
}