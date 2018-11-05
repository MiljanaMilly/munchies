package com.munchies.validators;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SignUpValidatorCheck.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SignUpValidator {

    String message() default "{com.munchies.validators.SignUpValidator.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
