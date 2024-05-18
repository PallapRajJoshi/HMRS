package com.hotelmanagementsystem.validation;


import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdultValidator.class)
@Documented
public @interface Adult {
    String message() default "Date of birth must be at least 18 years ago";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
