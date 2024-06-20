package com.hotelmanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name="feedback")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
private String name;
private String email;
@NotBlank(message = "Message must not be empty")
@Lob
private String message;
}
