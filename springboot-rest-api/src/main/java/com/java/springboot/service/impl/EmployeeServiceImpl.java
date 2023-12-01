package com.java.springboot.service.impl;

import com.java.springboot.dto.EmployeeDto;
import com.java.springboot.entity.Employee;
import com.java.springboot.exception.ResourceNotFoundException;
import com.java.springboot.repository.EmployeeRepository;
import com.java.springboot.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
@Autowired
    private EmployeeRepository employeeRepository;
@Autowired
private ModelMapper modelMapper;

/*private EmployeeDto convertEntityToDto(Employee employee){
    EmployeeDto employeeDto = new EmployeeDto();
    employeeDto.setId(employee.getId());
    employeeDto.setAge(employee.getAge());
    employeeDto.setEmpname(employee.getEmpname());
    employeeDto.setEmprole(employee.getEmprole());
    employeeDto.setGender(employee.getGender());
    return employeeDto;

}*/

    private  EmployeeDto convertEntityToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    //@Override
   // public List<Employee> getAllEmployees() {
      //  return employeeRepository.findAll();
   // }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee= employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        else{
            throw new ResourceNotFoundException("Employee", "id", id);
        }

    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        Employee updateEmployee = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee","id",id));;
       employeeRepository.save(updateEmployee);


        return updateEmployee;
    }

    @Override
    public void deleteEmployee(long id) {

        Employee employee= employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee","id",id));

        employeeRepository.deleteById(id);
    }
}
