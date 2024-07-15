package com.somesh.Spring_boot.FirstProject.Controller;

import com.somesh.Spring_boot.FirstProject.entity.Department;
import com.somesh.Spring_boot.FirstProject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    //This is post method
    //We return a Department object, we get the data in json format in post operations, so we use
    //@RequestBody Department department to convert the request body to a department object
    //Converting JSON object to Department Object
    @PostMapping("/departments") //Annotating Post API endpoint
    public Department saveDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

}
