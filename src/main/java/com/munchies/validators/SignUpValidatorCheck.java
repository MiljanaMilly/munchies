package com.munchies.validators;

import com.munchies.repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SignUpValidatorCheck implements ConstraintValidator<SignUpValidator, String> {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public void initialize(SignUpValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null && userJpaRepository.findByEmail(email) == null;
    }
}
