package com.hotelmanagementsystem.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotelmanagementsystem.model.Customer;

import com.hotelmanagementsystem.service.CustomerService;
import com.hotelmanagementsystem.utils.MailUtils;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerservice;
	@Autowired
	MailUtils mail;
	Customer customer;
	Customer customers;
// this is for verification code
	int randomNumber;
// sign up verification
	int rNumber;

	@GetMapping({"/clogin","customerlogin"})
	public String getLogin() {
		return "UserLoginForm";

	}
	
	
	
	
	@PostMapping({"/clogin","customerlogin"})
	public String postLogin(@ModelAttribute Customer user, Model m, HttpSession session) {
		user.setCpassword(DigestUtils.md5DigestAsHex(user.getCpassword().getBytes()));
		Customer usr = customerservice.userLogin(user.getCemail(), user.getCpassword());
		if (usr != null) {
			
			session.setAttribute("cactiveuser", usr);
			session.setMaxInactiveInterval(900);
			return "redirect:/index";
		}
		m.addAttribute("message", "User not Found");
		return "UserLoginForm";
	}
	
	
	
	
	
	

	@GetMapping({"/csignup","customersignup"})
	public String getSignup(Model model) {
		 model.addAttribute("user", new Customer());
		return "UserSignUp";
		
	}
	
	
	
	
	@PostMapping("/csignup")
	public String postSignUp(@Valid @ModelAttribute Customer user,BindingResult result, Model m, HttpSession session) {
		
		 if (result.hasErrors()) {
	            return "UserSignUp"; // Return to the signup form if validation fails
	        }else {
	        	
	    		// encryption
	    		user.setCpassword(DigestUtils.md5DigestAsHex(user.getCpassword().getBytes()));
	    		 customers = user;
	    		String emailV = customers.getCemail();
	    		rNumber = generateRandomNumber(1000, 9999);

	    		mail.sendEmail(emailV, "From Api, Don't share this code With others", "Your Verification code is   " + rNumber);

	    		m.addAttribute("code",
	    				"An email containing the verification code has been sent to your email address. Please check your inbox and enter the code.");

	    		return "UserEmailVeryfication";
	        }
	

	}

//Verifying email when getting register
	@PostMapping("/cregisterVerification")
	public String postVerify(Model m, HttpSession session, @RequestParam("cvcode") Integer cverificationCode) {

//emailV=user.getEmail();

		if (cverificationCode.equals(rNumber)) {

			customerservice.userSignup(customers);
			m.addAttribute("account", "Account Created");
			return "UserLoginForm";

		} else {
			m.addAttribute("message", "Verification Code Doesnot Match");
			return "UserEmailVeryfication";
		}
//		userService.userSignup(user);

	}

	@GetMapping("/cregisterVerification")
	public String getVerify() {
		return "UserEmailVeryfication";
	}

	@GetMapping("/clogout")
	public String getLogout(HttpSession session) {
		session.invalidate();
		return "UserLoginForm";
	}

	// forget password
	@GetMapping("/cforget")
	public String forgetPass() {
		return "UserForgetPasswordForm";
	}

	@PostMapping("/cforget")
	public String postForgetPass(@RequestParam String cemail, Model m) {

		customer = customerservice.passwordForget(cemail);
		if (customer!= null) {

			randomNumber = generateRandomNumber(1000, 9999);
			mail.sendEmail(cemail, "From Api, Don't share this code With others",
					"Your Verification code is" + randomNumber);

			m.addAttribute("forgetCode",
					"An email containing the verification code has been sent to your email address. Please check your inbox and enter the code to proceed.");

			m.addAttribute("vcode", randomNumber);

			return "UserNewPassword";
		}

		else {
			m.addAttribute("forgetPass", "The email provided is not registered.");
			return "UserSignUp";
		}

	}

	// Changing process
	@GetMapping("/cchange")
	public String getChange(Model m) {

		return "UserNewPassword";
	}

	// Changing process last process
	@PostMapping("/cchange")
	public String postChange(Model m, @RequestParam Integer cvcode, @RequestParam String cpassword,
			@RequestParam String ccpassword) {

		if (cvcode.equals(randomNumber)) {

			if (cpassword.equals(ccpassword)) {
				customer.setCpassword(DigestUtils.md5DigestAsHex(cpassword.getBytes()));

				customerservice.updatePassword(customer);

				// update

				m.addAttribute("passchanged", "Password Changed ");
				return "UserLoginForm";

			} else {
				m.addAttribute("message", "Password Doesnot match ");
				return "UserNewPassword";
			}

		} else {

			m.addAttribute("message", "Veryfication code  Doesnot match ");
			return "UserNewPassword";

		}

	}

	// method for generating random number
	public static int generateRandomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min; // Generates a random number within the specified range
	}
	
}