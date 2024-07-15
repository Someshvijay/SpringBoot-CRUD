# Notes for springBoot

```text
Springboot does auto configuration, but if we need to do manual 
config we can use application.properties file and do so.
Documentation available that we can ue to change config of 
application property by referring all properties in that doc.
```




### Reference

```
@RestController            // @Component, @Controller and will return a @ResponseBody

@RequestMapping(value = "/", method = RequestMethod.GET)  //whenever we go to localhost:8080 this particular method will be called

@GetMapping("/")  // Instead of using @RequestMapping(value = "/", method = RequestMethod.GET) for get request we can use this

@Entity //to make this class interact with database

@Id //specifying primary key
@GeneratedValue(strategy = GenerationType.AUTO) //Define how the primary key is generated
private Long departmentId;
    
@Service  //To denotes it is a service, usually service deals with business logic    

@Repository  //In Spring Boot, a repository is used to manage data persistence and retrieval in a database


```

### Things to Note
```java
// JpaRepository<Department, Long> == <Department - Entity> <Long - primary key type>
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

```


### Spring H2 Setup 

#### application.properties
```text
spring.h2.console.enabled=true  //to enable H2 console
spring.datasource.url=jdbc:h2:mem:dcbapp //Database url
spring.datasource.driverClassName=org.h2.Driver //Driver class
spring.datasource.username=sa //Username
spring.datasource.password=password    //Password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect  //jpa Database platform

-------------------------------------------------------------------------------------

Go to http://localhost:8080/h2-console to access console

```


#### Repository - handel's the transaction operation between entity and database.
#### Controller - handel's the rest endpoints
```java
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
```
#### Service - handel's the main business logic of these operations,
```java
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

```
#### Entity - behaves like a schema