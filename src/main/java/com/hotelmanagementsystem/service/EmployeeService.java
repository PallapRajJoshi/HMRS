package com.hotelmanagementsystem.service;

import java.util.List;

import com.hotelmanagementsystem.model.Employee;



public interface EmployeeService {
	void addEmp(Employee emp);
	void deleteEmp(Long id);
	void updateEmp(Employee emp);
	Employee getDeptById(Long id);
	List<Employee> getAllEmp();

}
