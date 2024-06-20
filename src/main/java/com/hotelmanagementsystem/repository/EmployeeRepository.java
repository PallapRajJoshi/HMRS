package com.hotelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagementsystem.model.Employee;



public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	

}
