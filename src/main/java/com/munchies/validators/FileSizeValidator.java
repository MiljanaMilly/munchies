package com.munchies.validators;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileSizeCheck.class)
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSizeValidator {


    String message() default "{com.munchies.validators.FileSizeValidator.message}" + "{file.size.limit}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
