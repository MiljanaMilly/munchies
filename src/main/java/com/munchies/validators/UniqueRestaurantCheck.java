package com.munchies.validators;

import com.munchies.dto.RestaurantDto;
import com.munchies.model.Restaurant;
import com.munchies.repositories.RestaurantJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueRestaurantCheck implements ConstraintValidator<UniqueRestaurantValidator, RestaurantDto> {

    @Autowired
    private RestaurantJpaRepository restaurantJpaRepository;

    @Override
    public void initialize(UniqueRestaurantValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(RestaurantDto restaurantDto, ConstraintValidatorContext constraintValidatorContext) {

        if (restaurantDto.getId() != null) {
            Restaurant restaurantinDb = restaurantJpaRepository.findById(restaurantDto.getId()).get();
            if (restaurantDto.getId().equals(restaurantinDb.getId()) && restaurantDto.getName().equalsIgnoreCase(restaurantinDb.getName())) {
                return true;
            } else if (restaurantJpaRepository.findByNameLike(restaurantDto.getName()).isPresent()) {
                return false;
            } else {
                return true;
            }
        } else {
            return !restaurantJpaRepository.findByNameLike(restaurantDto.getName()).isPresent();
        }
    }
}
