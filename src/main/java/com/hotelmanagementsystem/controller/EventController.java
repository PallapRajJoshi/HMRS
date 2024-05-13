package com.hotelmanagementsystem.controller;

import java.io.IOException;
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

import com.hotelmanagementsystem.model.Event;
import com.hotelmanagementsystem.service.EventService;

@Controller
public class EventController {
	@Autowired
	EventService eventser;

	@PostMapping("/addevents")
	public String postEvent(@RequestParam("event_img") MultipartFile img, @ModelAttribute Event e, Model m) {
		if (!img.isEmpty()) {
			String nameofimage = img.getOriginalFilename();
			try {
				// Save the image file to the appropriate location
				Files.copy(img.getInputStream(),
						Path.of("src/main/resources/static/image/" + img.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);
				// Set the URL of the uploaded image to the event_image field
				String url = "/image/" + nameofimage;
				e.setEvent_image(url);
				// Add the event to the service layer
				eventser.addEvent(e);
			} catch (IOException x) {
				x.printStackTrace();
				m.addAttribute("message", "Error uploading image");
				return "AddEvents";
			}

			m.addAttribute("message", "Event Added");
			return "AddEvents";
		} else {
			m.addAttribute("message", "Upload Fail");
			return "AddEvents";
		}
	}

	@GetMapping("/addeventdetails")
	public String getEventForm() {
		return "AddEvents";
	}

	@GetMapping("/listevent")
	public String getEventList(Model m) {
		m.addAttribute("eventList", eventser.getAllEvent());
		return "ListEventDetail";
	}

	@GetMapping("/edit/event")
	public String getEventEditForm(@RequestParam int id, Model model) {

		model.addAttribute("eventdetail", eventser.getEventById(id));

		return "EventEditForm";
	}

	@GetMapping("/update/event")
	public String getUpdateEvent(@RequestParam("event_image_update") MultipartFile img, @ModelAttribute Event event,
			Model m) {

		if (!img.isEmpty()) {

			String nameofimage = img.getOriginalFilename();
			try {
				Files.copy(img.getInputStream(),
						Path.of("src/main/resources/static/image/" + img.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);

				String url = "/image/" + nameofimage;
				event.setEvent_image(url);

				eventser.updateEvent(event);

			} catch (Exception e) {
				e.printStackTrace();
			}
			m.addAttribute("updated", "Room Details Updated");
			return "redirect:/listroom";

		} else {
			return "Hello";
		}

	}

	@PostMapping("/update/event")
	public String postEventUpdate(@RequestParam("event_image_update") MultipartFile img, @ModelAttribute Event event,
			Model m) {
		if (!img.isEmpty()) {

			String nameofimage = img.getOriginalFilename();
			try {
				Files.copy(img.getInputStream(),
						Path.of("src/main/resources/static/image/" + img.getOriginalFilename()),
						StandardCopyOption.REPLACE_EXISTING);

				String url = "/image/" + nameofimage;
				event.setEvent_image(url);

				eventser.updateEvent(event);

			} catch (Exception e) {
				e.printStackTrace();
			}
			m.addAttribute("updated", "Room Details Updated");
			return "redirect:/listevent";

		} else {
			return "Hello";
		}

	}

	@GetMapping("/delete/event")
	public String getDeleteEvent(@RequestParam int id) {
		eventser.deleteEvent(id);

		return "redirect:/listevent";

	}
}
