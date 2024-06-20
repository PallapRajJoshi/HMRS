package com.hotelmanagementsystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.model.Employee;
import com.hotelmanagementsystem.repository.EmployeeRepository;
import com.hotelmanagementsystem.service.EmployeeService;



@Service
public class EmployeeServiceImp implements EmployeeService {
@Autowired
private EmployeeRepository empRepo;
	@Override
	public void addEmp(Employee emp) {
	empRepo.save(emp);
		
	}

	@Override
	public void deleteEmp(Long id) {
		empRepo.deleteById( id);
		
	}

	@Override
	public void updateEmp(Employee emp) {
		empRepo.save(emp);
		
	}

	@Override
	public Employee getDeptById(Long id) {
		// TODO Auto-generated method stub
		return empRepo.findById(id).get();
	}

	@Override
	public List<Employee> getAllEmp() {
		// TODO Auto-generated method stub
		return empRepo.findAll();
	}

}
