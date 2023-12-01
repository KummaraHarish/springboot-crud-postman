package com.java.springboot.controller;

import com.java.springboot.dto.EmployeeDto;
import com.java.springboot.entity.Employee;
import com.java.springboot.response.ResponseHandler;
import com.java.springboot.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
@Autowired
private EmployeeService employeeService;

@Autowired
   private ModelMapper modelMapper;

    @PostMapping()
    public ResponseEntity<Object> saveEmployee(@RequestBody EmployeeDto employeeDto){
        Employee employeeRequest = modelMapper.map(employeeDto, Employee.class);

        Employee employee= employeeService.saveEmployee(employeeRequest);

        EmployeeDto employeeResponse = modelMapper.map(employee,EmployeeDto.class);
        return  ResponseHandler.responseBuilder("Employee created successfully",
                "S0000",
                HttpStatus.CREATED, new ResponseEntity<EmployeeDto>(employeeResponse,HttpStatus.CREATED));



       // return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Object> getAllEmployees(){
        return  ResponseHandler.responseBuilder(" All Employees data fetched  successfully",
                "S0000",
                HttpStatus.OK,employeeService.getAllEmployees());

    }
@GetMapping("{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable("id") long id){
    Employee employee=employeeService.getEmployeeById(id);

    EmployeeDto employeeDto =modelMapper.map(employee, EmployeeDto.class);

     return  ResponseHandler.responseBuilder("Employee data fetched with Id successfully",
            "S0000",
            HttpStatus.OK,ResponseEntity.ok().body(employeeDto));

      //  return ResponseEntity.ok().body(employeeDto);
    }
    @PutMapping("{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable("id") long id , @RequestBody EmployeeDto employeeDto){

        Employee employeeRequest = modelMapper.map(employeeDto, Employee.class);

        Employee employee= employeeService.saveEmployee(employeeRequest);

        EmployeeDto employeeResponse = modelMapper.map(employee,EmployeeDto.class);

        return ResponseHandler.responseBuilder("Employee details updated successfully",
                "S0000",
                HttpStatus.OK,ResponseEntity.ok().body(employeeResponse));


       // return ResponseEntity.ok().body(employeeResponse);

        // return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.OK);

    }
@DeleteMapping("{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("id") long id){

        employeeService.deleteEmployee(id);
    return ResponseHandler.responseBuilder("Employee details deleted successfully",
            "E0000",
            HttpStatus.OK,new ResponseEntity<String>("Employee Deleted Sucessfully",HttpStatus.OK));
      //  return new ResponseEntity<String>("Employee Deleted Sucessfully",HttpStatus.OK);
    }



}
