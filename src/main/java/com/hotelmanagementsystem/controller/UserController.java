package com.hotelmanagementsystem.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hotelmanagementsystem.model.User;
import com.hotelmanagementsystem.repository.UserRepository;
import com.hotelmanagementsystem.service.UserService;
import com.hotelmanagementsystem.utils.MailUtils;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller

public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	MailUtils mail;
	User u;

	// this is for verification code
	int randomNumber;
	// sign up verification
	int rNumber;
	User usr;

	@GetMapping("/login")
	public String getLogin() {
		return "LoginForm";

	}

	
	
	
	   @GetMapping("/backtoadminhome")
	    public String backButton(HttpSession session) {
	        if (session.getAttribute("activeuser") != null) {
	            return "AdminHome";
	        }else {
	        return "redirect:/login";
	        }
	    }
   

	

	
	
	@PostMapping("/login")
	public String postLogin(@ModelAttribute User user, Model m, HttpSession session) {
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		User usr = userService.userLogin(user.getEmail(), user.getPassword());
		if (usr != null) {
			log.info("-------User Login Success-----------");
			session.setAttribute("activeuser", usr);
			session.setMaxInactiveInterval(900);
			return "AdminHome";
		}
		m.addAttribute("message", "User not Found");
		return "LoginForm";
	}

	@GetMapping("/signup")
	public String getSignup(Model model, HttpSession session) {
		if (session.getAttribute("activeuser") == null || session.getAttribute("activeuser").equals("")) {
			model.addAttribute("messagelogin", "Login First");
			return "LoginForm";
		} else {
			model.addAttribute("user", new User());
			return "SignUp";
		}
	}

	@PostMapping("/signup")
	public String postSignUp(@Valid @ModelAttribute User user, BindingResult result, Model m, HttpSession session) {
		if (session.getAttribute("activeuser") == null || session.getAttribute("activeuser").equals("")) {
			m.addAttribute("messagelogin", "Login First");
			return "LoginForm";
		} else {
			if (result.hasErrors()) {
				return "SignUp"; // Return to the signup form if validation fails
			} else {

				// encryption
				user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
				usr = user;
				String emailV = usr.getEmail();
				rNumber = generateRandomNumber(1000, 9999);

				mail.sendEmail(emailV, "From Api, Don't share this code With others",
						"Your Verification code is" + rNumber);

				m.addAttribute("code",
						"An email containing the verification code has been sent to your email address. Please check your inbox and enter the code.");

				return "EmailVerification";
			}

		}
	}

//Verifying email when getting register
	@PostMapping("/registerVerification")
	public String postVerify(Model m, HttpSession session, @RequestParam("vcode") Integer verificationCode) {

//emailV=user.getEmail();

		if (verificationCode.equals(rNumber)) {

			userService.userSignup(usr);
			m.addAttribute("account", "Account Created");
			return "LoginForm";

		} else {
			m.addAttribute("message", "Verification Code Doesnot Match");
			return "EmailVerification";
		}
//		userService.userSignup(user);

	}

	@GetMapping("/registerVerification")
	public String getVerify() {
		return "EmailVerification";
	}

	@GetMapping("/logout")

	public String getLogout(HttpSession session) {

		session.invalidate();
		return "LoginForm";
	}

	// forget password
	@GetMapping("/forget")
	public String forgetPass() {
		return "ForgetPasswordForm";
	}

	@PostMapping("/forget")
	public String postForgetPass(@RequestParam String email, Model m) {

		u = userService.passwordForget(email);
		if (u != null) {

			randomNumber = generateRandomNumber(1000, 9999);
			mail.sendEmail(email, "From Api, Don't share this code With others",
					"Your Verification code is" + randomNumber);

			m.addAttribute("forgetCode",
					"An email containing the verification code has been sent to your email address. Please check your inbox and enter the code to proceed.");

			m.addAttribute("vcode", randomNumber);

			return "NewPasswordForm";
		}

		else {
			m.addAttribute("forgetPass", "The email provided is not registered.");
			return "SignUp";
		}

	}

	// Changing process
	@GetMapping("/change")
	public String getChange(Model m) {

		return "NewPasswordForm";
	}

	// Changing process last process
	@PostMapping("/change")
	public String postChange(Model m, @RequestParam Integer vcode, @RequestParam String password,
			@RequestParam String cpassword) {

		if (vcode.equals(randomNumber)) {

			if (password.equals(cpassword)) {
				u.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

				userService.updatePassword(u);

				// update

				m.addAttribute("passchanged", "Password Changed ");
				return "LoginForm";

			} else {
				m.addAttribute("message", "Password Doesnot match ");
				return "NewPasswordForm";
			}

		} else {

			m.addAttribute("message", "Veryfication code  Doesnot match ");
			return "NewPasswordForm";

		}

	}

	// method for generating random number
	public static int generateRandomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min; // Generates a random number within the specified range
	}

	// Booking Controller

}
