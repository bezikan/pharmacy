package com.example.pharmacy.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements ConstraintValidator<ContactNumberConstraint, String> {
    @Override
    public void initialize(ContactNumberConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext constraintValidatorContext) {
        return contactField != null && contactField.matches("[0-9]+")
                && (contactField.length() > 8) && (contactField.length() < 14);
    }
}
