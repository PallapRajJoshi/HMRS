package com.hotelmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotelmanagementsystem.model.Employee;
import com.hotelmanagementsystem.service.DepartmentService;
import com.hotelmanagementsystem.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService empSer;
	@Autowired
	private DepartmentService dptService;

	@GetMapping("/addemployee")
	public String getEmp(Model m) {

		m.addAttribute("dlist", dptService.getAllDepts());
		return "EmployeeForm";
	}

	@PostMapping("/employee")
	public String postEmp(@ModelAttribute Employee emp, Model m, HttpSession session) {
		if (session.getAttribute("activeuser") == null || session.getAttribute("activeuser").equals("")) {
			m.addAttribute("messagelogin", "Login First");
			return "LoginForm";
		} else {

			empSer.addEmp(emp);
			m.addAttribute("messageSuccess", "Employee Added");

			return "redirect:/addemployee";
		}
	}

	// employee list controlling
	@GetMapping("/employeeList")
	public String getEmployee(@ModelAttribute Employee e, Model m, HttpSession session) {
		if (session.getAttribute("activeuser") == null || session.getAttribute("activeuser").equals("")) {
			m.addAttribute("messagelogin", "Login First");
			return "LoginForm";
		} else {
			m.addAttribute("empList", empSer.getAllEmp());
			return "EmployeeList";
		}
	}

	@GetMapping("/emp/delete")
	public String deleteEmployee(@RequestParam long id, Model m, HttpSession session) {
		if (session.getAttribute("activeuser") == null || session.getAttribute("activeuser").equals("")) {
			m.addAttribute("messagelogin", "Login First");
			return "LoginForm";
		} else {
			empSer.deleteEmp(id);
			return "redirect:/employeeList";
		}

	}
}
