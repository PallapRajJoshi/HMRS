package com.hotelmanagementsystem.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@Controller
public class UploadController {
	@GetMapping("/upload")
	public String getUpload(HttpSession session,Model model) {
		if (session.getAttribute("activeuser") == null || session.getAttribute("activeuser").equals("")) {
			model.addAttribute("messagelogin", "Login First");
			return "LoginForm";
		}
			else {
		return "UploadPhoto";
	}}

	@PostMapping("/upload")
	public String postUpload( @RequestParam MultipartFile image,Model m,HttpSession session) {
		
		if (session.getAttribute("activeuser") == null || session.getAttribute("activeuser").equals("")) {
			m.addAttribute("messagelogin", "Login First");
			return "LoginForm";
		}
			else {
		if (!image.isEmpty()) {
			try {
				Files.copy(image.getInputStream(), Path.of("src/main/resources/static/image/"+image.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
				m.addAttribute("message", "File Upload Success");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			m.addAttribute("message","Upload Fail");
		}
		
		return "UploadPhoto";
	}
}}
