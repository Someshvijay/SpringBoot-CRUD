package com.somesh.Spring_boot.FirstProject.service;

import com.somesh.Spring_boot.FirstProject.entity.Department;
import com.somesh.Spring_boot.FirstProject.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    //uses the repository's save method to insert into database
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }
}
