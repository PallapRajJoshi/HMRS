package com.hotelmanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rooms_tbl")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//private int roomnumber;
	private String type;
	private String image;
	private int price;
	private Double size;
	private int capacity;
	private String service;
	private int bed;
	private int numberofroom;
}
