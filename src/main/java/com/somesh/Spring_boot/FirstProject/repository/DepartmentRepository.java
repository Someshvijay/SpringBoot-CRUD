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
