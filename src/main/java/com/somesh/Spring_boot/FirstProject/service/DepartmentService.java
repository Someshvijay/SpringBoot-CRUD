package com.somesh.Spring_boot.FirstProject.service;
import com.somesh.Spring_boot.FirstProject.entity.Department;


//To denotes it is a service, usually service deals with business logic
public interface DepartmentService {

    public Department saveDepartment(Department department);
}
