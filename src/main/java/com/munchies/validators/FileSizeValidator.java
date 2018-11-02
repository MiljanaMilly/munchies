package com.munchies.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileSizeCheck.class)
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSizeValidator {

    String message() default "{com.munchies.validators.FileSizeValidator.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
