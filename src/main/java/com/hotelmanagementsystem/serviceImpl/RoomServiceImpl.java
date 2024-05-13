package com.hotelmanagementsystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.model.Room;
import com.hotelmanagementsystem.repository.RoomRepository;
import com.hotelmanagementsystem.service.RoomService;
@Service
public class RoomServiceImpl implements RoomService{
@Autowired
RoomRepository roomrepo;
	@Override
	public void addRoom(Room room) {
		roomrepo.save(room);
		
	}

	@Override
	public void deleteRoom(int id) {
		roomrepo.deleteById(id);
		
	}

	@Override
	public List< Room> getAllRoom() {
		return roomrepo.findAll();
		
		
	}
	@Override
	public Room getRoomById(int id) {
		return roomrepo.getById(id);
		
	}
	
	@Override
	public void updateRoom(Room room) {
		roomrepo.save(room);
	}

}
