package com.hotelmanagementsystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.model.Event;
import com.hotelmanagementsystem.repository.EventRepository;
import com.hotelmanagementsystem.service.EventService;
@Service
public class EventServiceImpl implements EventService{
@Autowired
EventRepository event_repo;
	@Override
	public void addEvent(Event detail) {
		
		event_repo.save(detail);
	}
	@Override
	public List<Event> getAllEvent() {
		
		return event_repo.findAll();
	}
	@Override
	public void deleteEvent(int id) {
		event_repo.deleteById(id);
		
	}
	@Override
	public Event getEventById(int id) {
		return event_repo.getById(id);
		
	}
	@Override
	public void updateEvent(Event e) {
		event_repo.save(e);
		
	}

}
