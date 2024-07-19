package com.somesh.Spring_boot.FirstProject.repository;

import com.somesh.Spring_boot.FirstProject.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("ME")
                .departmentAddress("Delhi")
                .departmentCode("ME-11")
                .build();

        entityManager.persist(department);
        entityManager.flush();

    }

    @Test
    public void whenFindById_thenReturnDepartment(){
        Department department = departmentRepository.findById(1L).get();
        assertEquals(department.getDepartmentName(), "ME");
        assertEquals(department.getDepartmentAddress(), "Delhi");
        assertEquals(department.getDepartmentCode(), "ME-11");
    }
}