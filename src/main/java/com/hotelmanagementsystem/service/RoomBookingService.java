package com.hotelmanagementsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.model.RoomBooking;

public interface RoomBookingService {
	public void roomBooking(RoomBooking details);
	public List<RoomBooking> findRoomBooking();
	public void rejectBooking(int id);
	public void acceptBooking(int id);
	

	

}
