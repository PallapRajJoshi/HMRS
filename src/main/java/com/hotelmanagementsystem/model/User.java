package com.hotelmanagementsystem.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String fname;
private String lname;
@Column(unique=true)
private String email;
private String password;
private String gender;
@DateTimeFormat(iso=ISO.DATE)
private LocalDate dob;
private String phone;
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="addressId")
private Address address;
}

