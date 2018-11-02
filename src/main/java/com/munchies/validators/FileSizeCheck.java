package com.munchies.validators;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileSizeCheck implements ConstraintValidator<FileSizeValidator, MultipartFile> {

    @Override
    public void initialize(FileSizeValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile.getSize() > 2000000) {
            return false;
        }
        return true;
    }
}
