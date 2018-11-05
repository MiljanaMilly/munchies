package com.munchies.validators;

import com.munchies.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchCheck implements ConstraintValidator<FieldMatchValidator, UserDto> {


    @Override
    public void initialize(FieldMatchValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userDto.getPassword().equals(userDto.getConfirmPassword());
    }
}
