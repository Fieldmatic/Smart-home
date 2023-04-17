package com.bsep.smart.home.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyValidator.class)
public @interface CommonPasswordValidation {
    String message() default "Commonly used password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
