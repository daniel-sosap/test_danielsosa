package com.dsp.restdsl.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;

public class IsNumericValidator implements ConstraintValidator<IsNumeric, Object> {
    private String fieldName;
    private String message;

    @Override
    public void initialize(IsNumeric constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if (object == null) {
            addConstraintViolation(context, "The '" + fieldName + "' must not be empty");
            return false;
        }

        try {
            Integer.parseInt(object.toString());
        } catch (NumberFormatException e) {
            addConstraintViolation(context, message);
            return false;
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation().disableDefaultConstraintViolation();
    }
}
