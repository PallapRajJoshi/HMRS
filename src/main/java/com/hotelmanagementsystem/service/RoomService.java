package com.hotelmanagementsystem.service;



import java.util.List;



import com.hotelmanagementsystem.model.Room;

public interface RoomService  {
public void addRoom(Room room);
public void deleteRoom(int id);
public List< Room> getAllRoom();
public Room getRoomById(int id);
public void updateRoom(Room room);
}
