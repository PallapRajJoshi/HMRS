package com.hotelmanagementsystem.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "booking_tbl")
public class RoomBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "Check-in date is required")
	@FutureOrPresent(message = "Check-in date must be today or in the future")

	private LocalDate checkindate;
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "Check-out date is required")
	@Future(message = "Check-out date must be in the future")

	private LocalDate checkoutdate;
	@NotEmpty(message = "Number of guestes are required")
	private String numberofguests;
	@NotEmpty(message = "Number of room are required")
	private String numberofrooms;
	@NotEmpty(message = "Room type is required")
	private String roomtype;
	private String cusername;
	private String cemail;
	private boolean accepted;
	

}
