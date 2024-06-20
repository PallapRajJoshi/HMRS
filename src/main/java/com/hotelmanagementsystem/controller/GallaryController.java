package com.hotelmanagementsystem.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
@Controller
public class GallaryController {
@GetMapping("/gallery")
	public String getGallary(Model m,HttpSession session){
	if(session.getAttribute("activeuser")==null) {
		return"LoginForm";
	}
	String[] imageNameList=new File("src/main/resources/static/image").list();
	m.addAttribute("imgList",imageNameList);
		return "GalleryForm";
	}
}
