package com.hotelmanagementsystem.validation;

import com.hotelmanagementsystem.model.RoomBooking;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//Custom validator to ensure checkoutdate is after checkindate


public class CheckDateValidator implements ConstraintValidator<ValidDates, RoomBooking> {
 
 @Override
 public void initialize(ValidDates constraintAnnotation) {
 }

 @Override
 public boolean isValid(RoomBooking roombooking, ConstraintValidatorContext context) {
     if (roombooking.getCheckindate() == null || roombooking.getCheckoutdate() == null) {
         return true; // other validators will catch null values
     }
     return roombooking.getCheckoutdate().isAfter(roombooking.getCheckindate());
 }
}