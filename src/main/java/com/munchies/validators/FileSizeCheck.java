package com.munchies.validators;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileSizeCheck implements ConstraintValidator<FileSizeValidator, MultipartFile> {

    @Value("${file.size.limit}")
    private Long fileSize;

    @Override
    public void initialize(FileSizeValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile.getSize() > fileSize) {
            return false;
        }
        return true;
    }
}
