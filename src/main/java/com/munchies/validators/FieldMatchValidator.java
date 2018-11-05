package com.munchies.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchCheck.class)
public @interface FieldMatchValidator {

    String message() default "{com.munchies.validators.FieldMatchValidator.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
//    String[] fields();
//    String[] verifyFields();


}
