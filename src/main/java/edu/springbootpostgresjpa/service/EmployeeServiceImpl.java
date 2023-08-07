package edu.springbootpostgresjpa.service;

import edu.springbootpostgresjpa.exception.ResourceNotFoundException;
import edu.springbootpostgresjpa.model.Employee;
import edu.springbootpostgresjpa.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) throws ResourceNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent())
        {
            return employee.get();
        }else {
            throw new ResourceNotFoundException("Employee","id", id);
        }

    }

    @Override
    public Employee updateEmployee(Employee employee, long id) throws ResourceNotFoundException {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee","id",id));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) throws ResourceNotFoundException {
       employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee","id",id));
       employeeRepository.deleteById(id);
    }

}
