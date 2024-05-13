package com.hotelmanagementsystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.model.RoomBooking;
import com.hotelmanagementsystem.repository.RoomBookingRepository;
import com.hotelmanagementsystem.service.RoomBookingService;
@Service
public class RoomBookingServiceImpl implements RoomBookingService {
@Autowired 
RoomBookingRepository bookroom;
	@Override
	public void roomBooking(RoomBooking details) {
		bookroom.save(details);
		
	}
	@Override
	public List<RoomBooking> findRoomBooking() {
		// TODO Auto-generated method stub
		return bookroom.findAll();
	}
	@Override
	public void rejectBooking(int id) {
	bookroom.deleteById(id);
		
	}
	@Override
	public void acceptBooking(int id) {
		// TODO Auto-generated method stub
		
	}

}
