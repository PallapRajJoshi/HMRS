package com.hotelmanagementsystem.service;

import java.util.List;

import com.hotelmanagementsystem.model.Department;



public interface DepartmentService {
void addDept(Department dept);
void deleteDept(int id);
void updateDept(Department dept);
Department getDeptById(int id);
List<Department> getAllDepts();
}
