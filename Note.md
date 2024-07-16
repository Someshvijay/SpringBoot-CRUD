# Notes for springBoot

```text
Springboot does auto configuration, but if we need to do manual 
config we can use application.properties file and do so.
Documentation available that we can ue to change config of 
application property by referring all properties in that doc.
```

#### Refer https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html


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


## GET Request

Controller Class
```java
    @GetMapping("/departments")
    public List<Department> fetchDepartment(){
        return departmentService.fetchDepartmentList();
    }

    @GetMapping("/departments/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId ){
        return departmentService.fetchDepartmentById(departmentId);
    }
    
    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName){
        return departmentService.fetchDepartmentByName(departmentName);
    }
    

```

Service Class
```java
//uses the repository's findAll method to get all the data from the database
    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    //uses the repository's findById method to get the data by its id from the database
    //findById(departmentId) - will return optional we need to add ".get()" to get the values
    @Override
    public Department fetchDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).get();
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName);
    }

```

Repository Interface
```java
package com.somesh.Spring_boot.FirstProject.repository;

import com.somesh.Spring_boot.FirstProject.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //In Spring Boot, a repository is used to manage data persistence and retrieval in a database

// JpaRepository<Department, Long> == <Department - Entity> <Long - primary key type>
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    //E
    //custom repository method to get department by name, spring automatically does the logic
    //syntax should start with findBy{attribute name} in camel case correctly as it is in database.
    public Department findByDepartmentName(String departmentName);

    //custom method with ignore case
    public Department findByDepartmentNameIgnoreCase(String departmentName);
    
    //use - @Query()
    // public method --
    // and pass the jpl or sql native query for complex actions that cannot be given by spring jpa as it is.
}
```

## POST Request

Controller Class
```java
    @PostMapping("/departments") //Annotating Post API endpoint
    public Department saveDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }
```

Service Class
```java
//uses the repository's save method to insert into database
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }
```

## Delete Request

Controller Class
```java
    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return "Department Deleted Successfully";
    }
```

Service Class
```java
    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }
```

## PUT Request
Controller Class
```java
    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department){
        return departmentService.updateDepartment(departmentId,department);
    }
```

Service Class
```java
//Finding the data from the database by id and storing the department object into debDB
//checking if our the request for update has a not null and non-blank object for all attributes
//If so we need to update it,so we use setDepartment
    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department depDB = departmentRepository.findById(departmentId).get();
        if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())){
            depDB.setDepartmentName(department.getDepartmentName());
        }

        if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())){
            depDB.setDepartmentCode(department.getDepartmentCode());
        }

        if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())){
            depDB.setDepartmentAddress(department.getDepartmentAddress());
        }

        return departmentRepository.save(depDB);

    }
```