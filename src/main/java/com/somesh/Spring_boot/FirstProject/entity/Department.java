package com.somesh.Spring_boot.FirstProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity //to make this class interact with database
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id //specifying primary key
    @GeneratedValue(strategy = GenerationType.AUTO) //Define how the primary key is generated
    private Long departmentId;
    @NotBlank(message = "Please Add Department Name")
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;


}
