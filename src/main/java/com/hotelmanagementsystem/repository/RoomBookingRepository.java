package com.hotelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementsystem.model.RoomBooking;

public interface RoomBookingRepository extends JpaRepository<RoomBooking, Integer> {

}
