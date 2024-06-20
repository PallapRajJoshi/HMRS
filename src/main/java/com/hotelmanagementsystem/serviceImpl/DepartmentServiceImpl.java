package com.hotelmanagementsystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelmanagementsystem.model.Department;
import com.hotelmanagementsystem.repository.DEpartmentRepository;
import com.hotelmanagementsystem.service.DepartmentService;



@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
private DEpartmentRepository depRepo;
	@Override
	public void addDept(Department dept) {
		depRepo.save(dept);
		
	}

	@Override
	public void deleteDept(int id) {
		depRepo.deleteById(id);
		
	}

	@Override
	public void updateDept(Department dept) {
		depRepo.save(dept);
		
	}

	@Override
	public Department getDeptById(int id) {
		// TODO Auto-generated method stub
		return depRepo.findById(id).get();
	}

	@Override
	public List<Department> getAllDepts() {
		// TODO Auto-generated method stub
		return depRepo.findAll();
	}

}
