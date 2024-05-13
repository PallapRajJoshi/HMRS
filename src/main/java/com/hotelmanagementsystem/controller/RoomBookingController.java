package com.hotelmanagementsystem.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotelmanagementsystem.model.RoomBooking;
import com.hotelmanagementsystem.service.RoomBookingService;
import com.hotelmanagementsystem.service.RoomService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RoomBookingController {
	@Autowired
	RoomBookingService booking;
	@Autowired
	RoomService roomser;

	//
	@PostMapping("/checkavailability")
	public String postAvailability(@ModelAttribute RoomBooking bookingroom, Model m, HttpSession session) {
		
		
		
		
		
	session.setAttribute("bookingdetail", bookingroom);
		m.addAttribute("rList", roomser.getAllRoom());
		return "AvailabilRoomList";
		
		
		
		
		

	}

	@GetMapping("/checkavailability")
	public String getAvailability(@ModelAttribute RoomBooking bookingroom, Model m,HttpSession session) {
		session.setAttribute("bookingdetail", bookingroom);
		m.addAttribute("rList", roomser.getAllRoom());
		return "AvailabilRoomList";

	}

	// booking
	@PostMapping("/bookroom")
	public String postBookRoom( Model model,HttpSession session) {
RoomBooking bookingroom=(RoomBooking)session.getAttribute("bookingdetail");
		booking.roomBooking(bookingroom);
		model.addAttribute("message",
				"Booking Request Sent Successfully!");
		return "BOokingConformation";

	}

	@GetMapping("/bookroom")
	public String getBookRoom( Model model,HttpSession session) {
		RoomBooking bookingroom=(RoomBooking)session.getAttribute("bookingdetail");
		booking.roomBooking(bookingroom);
		model.addAttribute("message",
				"Booking Request Sent Successfully!");
		return "BOokingConformation";
	}
	// booking request

	@GetMapping("/bookingrequest")
	public String bookingRequest(Model m, @ModelAttribute RoomBooking roombooking) {

		m.addAttribute("bookingrequest", booking.findRoomBooking());

		return "BookingRequestForm";
	}

	@PostMapping("/bookingrequest")
	public String postBookingRequest(Model m, @ModelAttribute RoomBooking roombooking) {

		m.addAttribute("bookingrequest", booking.findRoomBooking());

		return "BookingRequestForm";
	}
	// booking accept

	@GetMapping("/booking/accept")
	public String postAccept(@RequestParam int id) {
		
		
		return "redirect:/bookingrequest";
	}

	// booking Reject

	@GetMapping("/booking/reject")
	public String postReject(@RequestParam int id) {
		booking.rejectBooking(id);
		return "redirect:/bookingrequest";
	}

}