package com.hotelmanagementsystem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotelmanagementsystem.model.Customer;
import com.hotelmanagementsystem.model.RoomBooking;
import com.hotelmanagementsystem.service.EventService;
import com.hotelmanagementsystem.service.RoomBookingService;
import com.hotelmanagementsystem.service.RoomService;
import com.hotelmanagementsystem.utils.MailUtils;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class RoomBookingController {
	@Autowired
	RoomBookingService booking;
	@Autowired
	RoomService roomser;
	@Autowired
	EventService eventservice;
	@Autowired
	MailUtils mail;
	//
	@PostMapping("/checkavailability")
	public String postAvailability(@Valid @ModelAttribute("validRoomBooking") RoomBooking bookingroom,BindingResult result, Model m, HttpSession session) {

		 if (result.hasErrors()) {
			 m.addAttribute("roomlist", roomser.getAllRoom());
	            m.addAttribute("eventlist", eventservice.getAllEvent());
	            return "index";
	        }
		 
		 
		 else if (session.getAttribute("cactiveuser") == null || session.getAttribute("cactiveuser").equals("")) {
				m.addAttribute("messagelogin", "Login First");
				return "UserLoginForm";
		 }

		 else {
			 
			// Retrieve the customer object from the session
			 Customer customer = (Customer) session.getAttribute("cactiveuser");

			 // Set email and username to bookingroom object
			
			     bookingroom.setCemail(customer.getCemail());
			     bookingroom.setCusername(customer.getCfname());
			 
			 session.setAttribute("bookingdetail", bookingroom);
				m.addAttribute("rList", roomser.getAllRoom());
			
				return "AvailabilRoomList";
		 }
			
		
	}
//
//	@GetMapping("/checkavailability")
//	public String getAvailability(@Valid @ModelAttribute RoomBooking bookingroom,BindingResult result, Model m,HttpSession session) {
//		
//		 if (result.hasErrors()) {
//	            return "index";
//	            
//	        }else {
//	        	session.setAttribute("bookingdetail", bookingroom);
//	    		m.addAttribute("rList", roomser.getAllRoom());
//	    		return "AvailabilRoomList";
//	        }
//		
//
//	}

	// booking
	@PostMapping("/bookroom")
	public String postBookRoom(Model model, HttpSession session) {
		RoomBooking bookingroom = (RoomBooking) session.getAttribute("bookingdetail");
	
		booking.roomBooking(bookingroom);
		model.addAttribute("message", "Booking Request Sent Successfully!");
		return "BOokingConformation";

	}

	@GetMapping("/bookroom")
	public String getBookRoom(Model model, HttpSession session) {
		RoomBooking bookingroom = (RoomBooking) session.getAttribute("bookingdetail");
		booking.roomBooking(bookingroom);
		model.addAttribute("message", "Booking Request Sent Successfully!");
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
	public String postAccept(@RequestParam int id, @ModelAttribute RoomBooking roombooking) {
		 RoomBooking bookingDetails =  booking.acceptBooking(id);
		 bookingDetails.setAccepted(true);
		
		 booking.roomBooking( bookingDetails);
		
		String message="We are thrilled to confirm your hotel booking at ApiNampa hotel."
				+ " Your comfort and satisfaction are our top priorities, and we are committed to providing you with a"
				+ " memorable experience during your time with us."
				+ "Should you have any further inquiries or require assistance with anything before your arrival, "
				+ "please do not hesitate to contact us."
				+ " Our team is dedicated to ensuring that your stay is as seamless and enjoyable as possible"
				;
		mail.sendEmail(bookingDetails.getCemail(),"Booking Conformed", message );	
		
		
		
		return "redirect:/bookingrequest";
	}

	// booking Reject

//	@GetMapping("/booking/reject")
//	public String getReject(@RequestParam int id) {
//	
//		booking.rejectBooking(id);
//		return "redirect:/bookingrequest";
//	}

	
	
	@GetMapping("/booking/reject")
	public String postReject(@RequestParam int id,@ModelAttribute RoomBooking roombooking) {
		 RoomBooking bookingDetails =  booking.acceptBooking(id);
		String rejectionMessage = "We regret to inform you that your hotel booking at ApiNampa hotel has been declined."
			    + " While we appreciate your interest in staying with us, we are unable to accommodate your request at this time."
			    + " Should you have any further inquiries or wish to explore alternative booking options, please do not hesitate to contact us."
			    + " We apologize for any inconvenience this may cause and hope to have the opportunity to welcome you in the future.";

		mail.sendEmail(bookingDetails.getCemail(),"Booking Rejected", rejectionMessage );
		
		booking.rejectBooking(id);
		return "redirect:/bookingrequest";
	}
}