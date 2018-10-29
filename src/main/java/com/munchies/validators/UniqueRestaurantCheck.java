package com.munchies.validators;

import com.munchies.repositories.RestaurantJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueRestaurantCheck implements ConstraintValidator<UniqueRestaurantValidator, String> {

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Override
    public void initialize(UniqueRestaurantValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !restaurantJpaRepository.findByNameLike(s).isPresent();
    }
}
