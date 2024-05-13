package com.hotelmanagementsystem.service;



import java.util.List;

import com.hotelmanagementsystem.model.Event;

public interface EventService  {
	
	public void addEvent(Event detail);
	public List<Event> getAllEvent();
	public void deleteEvent(int id);
	public Event getEventById(int id);
	public void updateEvent(Event e);
	
	

}
