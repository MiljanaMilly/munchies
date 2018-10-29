package com.munchies.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueRestaurantCheck.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueRestaurantValidator {

    String message() default "{com.munchies.validators.UniqueRestaurant.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
