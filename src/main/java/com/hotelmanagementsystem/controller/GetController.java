package com.hotelmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.hotelmanagementsystem.model.RoomBooking;
import com.hotelmanagementsystem.service.EventService;
import com.hotelmanagementsystem.service.RoomService;

@Controller
public class GetController {
	@Autowired
	RoomService roomservice;
	@Autowired
	EventService eventservice;

	// get rooms details
	@GetMapping("/rooms")
	public String getRooms() {
		return "rooms";
	}
//Get Home Page

	@GetMapping("/")
	public String getHome(Model mrom) {
		mrom.addAttribute("validRoomBooking", new RoomBooking());
		mrom.addAttribute("roomlist", roomservice.getAllRoom());

		mrom.addAttribute("eventlist", eventservice.getAllEvent());

		return "index";
	}

	@GetMapping("/index")
	public String getHomeindex(Model m) {
		m.addAttribute("validRoomBooking", new RoomBooking());
		m.addAttribute("roomlist", roomservice.getAllRoom());
		m.addAttribute("eventlist", eventservice.getAllEvent());

		return "index";
	}

	// get about us
	@GetMapping("/about-us")
	public String getAbout() {
		return "about-us";
	}

	// get blog

	@GetMapping("/blog")
	public String getBlog() {
		return "blog";
	}

	// get pages

	@GetMapping("/pages")
	public String getPages() {
		return "pages";
	}

	// get room details

	@GetMapping("/room-details")
	public String getRoomDetails() {
		return "room-details";
	}

	// blog details
	@GetMapping("/blog-details")
	public String blogDetails() {
		return "blog-details";
	}

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
}
