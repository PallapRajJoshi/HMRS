package com.hotelmanagementsystem.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hotelmanagementsystem.model.Room;
import com.hotelmanagementsystem.service.RoomService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RoomController {
	@Autowired
	RoomService roomser;

	@GetMapping("/addroom")
	public String getRoom(HttpSession session, Model m) {

		if (session.getAttribute("activeuser") == null || session.getAttribute("activeuser").equals("")) {
			m.addAttribute("messagelogin", "Login First");
			return "LoginForm";
		} else {
		}
		return "AddRoomsForm";
	}

	@PostMapping("/addroom")
	public String postRoom(@RequestParam("room_image") MultipartFile img, @ModelAttribute Room room, Model m) {

		if (!img.isEmpty()) {

			String nameofimage = img.getOriginalFilename();
			try {
				Files.copy(img.getInputStream(),
						Path.of("src/main/resources/static/image/" + img.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
				String url = "/image/" + nameofimage;
				room.setImage(url);
				roomser.addRoom(room);
			} catch (IOException e) {
				e.printStackTrace();
			}

			m.addAttribute("message", "Room Added");
			return "AddRoomsForm";
		} else {
			m.addAttribute("message", "Upload Fail");

			return "AddRoomsForm";
		}

	}

	// Room List COntroller

	@GetMapping("/listroom")
	public String getListRoom(Model model) {

		model.addAttribute("rList", roomser.getAllRoom());

		return "ListOfRoom";
	}

	// delete room controller

	@GetMapping("/roomdetail/delete")
	public String deleteRoom(@RequestParam int id) {
		roomser.deleteRoom(id);
		return "redirect:/listroom";
	}

	// Edit Room detail Controller
	@GetMapping("/roomdetail/edit")
	public String editRoom(@RequestParam int id, Model m) {

		m.addAttribute("roomModel", roomser.getRoomById(id));

		return "EditRoomForm";
	}

//update room details
	@PostMapping("/update/room")
	public String updateRoomPost(@RequestParam("image_room_update") MultipartFile img, @ModelAttribute Room room, Model m) {
		
		if (!img.isEmpty()) {

			String nameofimage = img.getOriginalFilename();
			try {
				Files.copy(img.getInputStream(), Path.of("src/main/resources/static/image/" + img.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);

				String url = "/image/" + nameofimage;
				room.setImage(url);
				
				roomser.updateRoom(room);

			} catch (Exception e) {
				e.printStackTrace();
			}
			m.addAttribute("updated", "Room Details Updated");
			return "redirect:/listroom";

		} else {
			return "Hello";
		}
	}
}
