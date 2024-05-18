package com.hotelmanagementsystem.validation;

//Custom annotation

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CheckDateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDates {
	
 String message() default "Check-out date must be after check-in date";
 Class<?>[] groups() default {};
 Class<? extends Payload>[] payload() default {};
}