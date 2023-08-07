package edu.springbootpostgresjpa.controller;

import edu.springbootpostgresjpa.exception.ResourceNotFoundException;
import edu.springbootpostgresjpa.model.Employee;
import edu.springbootpostgresjpa.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    // build create employee REST API
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // build all employee

    @GetMapping
    public List<Employee> getAllEmployees(){
           return employeeService.getAllEmployees();
    }

    // build get all employee by id

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) throws ResourceNotFoundException {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);

    }
    // update
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
                                                   @RequestBody Employee employee) throws ResourceNotFoundException {
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) throws ResourceNotFoundException {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee deleteSuccessfully ", HttpStatus.OK);

    }
}
