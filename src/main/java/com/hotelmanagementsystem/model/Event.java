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
@Table(name="event_details")
public class Event {
@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)

private int event_id;
private String event_location;
	private String event_type;
	private String event_image;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate event_date;
}
