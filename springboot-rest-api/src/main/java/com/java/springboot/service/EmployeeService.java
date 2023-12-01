package com.java.springboot.service;

import com.java.springboot.dto.EmployeeDto;
import com.java.springboot.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<EmployeeDto> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Employee employee, Long id);

    void deleteEmployee(long id);

}
