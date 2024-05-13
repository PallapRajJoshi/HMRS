package com.hotelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementsystem.model.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
