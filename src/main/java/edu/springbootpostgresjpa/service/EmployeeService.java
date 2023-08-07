package edu.springbootpostgresjpa.service;

import edu.springbootpostgresjpa.exception.ResourceNotFoundException;
import edu.springbootpostgresjpa.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(long id) throws ResourceNotFoundException;
    Employee updateEmployee(Employee employee,long id) throws ResourceNotFoundException;
    void deleteEmployee(long id) throws ResourceNotFoundException;
}
