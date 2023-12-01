package com.java.springboot.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String empname;
    private String emprole;
    private String gender;
    private int age;

}
