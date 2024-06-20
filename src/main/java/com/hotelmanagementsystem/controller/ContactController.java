package com.hotelmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.hotelmanagementsystem.model.Contact;
import com.hotelmanagementsystem.model.Customer;
import com.hotelmanagementsystem.service.ContactService;
import com.hotelmanagementsystem.service.CustomerService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class ContactController {
	@Autowired
	private CustomerService customerservice;
	@Autowired
	ContactService contactservice;
	

	
	
	@PostMapping("/feedback")
	public String postContact(@ModelAttribute @Valid Contact contact, BindingResult bindingResult, HttpSession session,Model m) {
		Customer customer=(Customer) session.getAttribute("cactiveuser");
		  if (bindingResult.hasErrors()) {
	            // If validation errors, return to the form with error messages
	            return "redirect:/contact"; 
	        }
		  else if (session.getAttribute("cactiveuser") == null || session.getAttribute("cactiveuser").equals("")) {
			m.addAttribute("messagelogin", "Login First");
			return "redirect:/clogin";
	 }
		else {
			
			contactservice.sendFeedback(contact);
			return "redirect:/contact";
			
		}
		
		
		
		
	}
	
	
	
	@GetMapping("/showfeedback")
	public String showFeedback(Model model,HttpSession session) {
		if (session.getAttribute("activeuser") == null || session.getAttribute("activeuser").equals("")) {
			model.addAttribute("messagelogin", "Login First");
			return "LoginForm";
		}else {
		model.addAttribute("contactinfo", contactservice.getAllContact());
		return"feedback";
	}}
	

}
