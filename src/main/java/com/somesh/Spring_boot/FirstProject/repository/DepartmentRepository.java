package com.somesh.Spring_boot.FirstProject.repository;

import com.somesh.Spring_boot.FirstProject.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //In Spring Boot, a repository is used to manage data persistence and retrieval in a database

// JpaRepository<Department, Long> == <Department - Entity> <Long - primary key type>
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
