package com.hotelmanagementsystem.validation;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {

    @Override
    public void initialize(Adult constraint) {
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return true; // Let @NotNull or other annotations handle this
        }
        
        // Calculate age based on current date
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        int age = period.getYears();
        
        // Check if age is at least 18
        return age >= 18;
    }
}
