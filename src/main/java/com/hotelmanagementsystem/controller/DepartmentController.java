package com.hotelmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hotelmanagementsystem.model.Department;
import com.hotelmanagementsystem.service.DepartmentService;

import jakarta.servlet.http.HttpSession;




@Controller
public class DepartmentController {
	@Autowired
	DepartmentService ds;

	@GetMapping("/department")
	public String getDepartment() {
		return "Department";

	}

	@PostMapping("/department")
	public String postDepartment(@ModelAttribute Department dept, org.springframework.ui.Model m,HttpSession session) {
		if (session.getAttribute("activeuser") == null || session.getAttribute("activeuser").equals("")) {
			m.addAttribute("messagelogin", "Login First");
			return "LoginForm";
		} else {

		ds.addDept(dept);
		m.addAttribute("message", "Department Details Added Successfully");

		return "Department";
	}}

	@GetMapping("/departmentlist")
	public String getDepartmentList(@ModelAttribute Department dpt, org.springframework.ui.Model m,HttpSession session) {
		if (session.getAttribute("activeuser") == null || session.getAttribute("activeuser").equals("")) {
			m.addAttribute("messagelogin", "Login First");
			return "LoginForm";
		} else {
		m.addAttribute("dlist", ds.getAllDepts());
		return "DepartmentListForm";
	}
	}
	@GetMapping("/dept/delete")
	public String delete(@RequestParam int id,Model m,HttpSession session) {
		if (session.getAttribute("activeuser") == null || session.getAttribute("activeuser").equals("")) {
			m.addAttribute("messagelogin", "Login First");
			return "LoginForm";
		} else {
		ds.deleteDept(id);
		return "redirect:/departmentlist";
	}}
//@RequestParam for taking id value
	@GetMapping("/dept/edit")
	public String edit(@RequestParam int id, org.springframework.ui.Model m,HttpSession session) {
		if (session.getAttribute("activeuser") == null || session.getAttribute("activeuser").equals("")) {
			m.addAttribute("messagelogin", "Login First");
			return "LoginForm";
		} else {
		m.addAttribute("dModel", ds.getDeptById(id));

		return "DepartmentEditForm";
	}
	}
	@PostMapping("/dept/update")
	public String update(@ModelAttribute Department dept,Model m,HttpSession session) {
		if (session.getAttribute("activeuser") == null || session.getAttribute("activeuser").equals("")) {
			m.addAttribute("messagelogin", "Login First");
			return "LoginForm";
		} else {
		ds.updateDept(dept);
		return "redirect:/departmentlist";

	}
	}
	
	//excel ,PDF generator
//	@GetMapping("/dept/excel")
//	public ModelAndView excel() {
//		ModelAndView mv=new ModelAndView();
//		mv.addObject("dlist",ds.getAllDepts());
//		mv.setView(new DepartmentExcelView());
//		return mv;
//		
//	}
//	
	
//	@GetMapping("/dept/pdf")
//	public ModelAndView pdf() {
//		ModelAndView mp=new ModelAndView();
//		mp.addObject("dlist",ds.getAllDepts());
//		mp.setView(new DepartmentPdfView());
//		return mp;
//		
//	}
	
}
