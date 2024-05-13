package com.hotelmanagementsystem.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="booking_tbl")
public class RoomBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@DateTimeFormat(iso=ISO.DATE)
private LocalDate checkindate;
@DateTimeFormat(iso=ISO.DATE)
private LocalDate checkoutdate;
private String numberofguests;
private String numberofrooms;
}
