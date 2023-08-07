package edu.springbootpostgresjpa.controller;

import edu.springbootpostgresjpa.exception.ResourceNotFoundException;
import edu.springbootpostgresjpa.model.Customer;
import edu.springbootpostgresjpa.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gov/customer")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // build create customer REST API
    @PostMapping("/saveCustomer")
    public ResponseEntity<Customer> saveCustomerController(@RequestBody Customer customer) {
        return new ResponseEntity<Customer>(customerService.saveCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomersController() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<Customer> getCustomerByIdController(@PathVariable("id") long customerId) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);

    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomerController(@PathVariable("id") long id,
                                                             @RequestBody Customer customer) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.updateCustomer(customer, id), HttpStatus.OK);

    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomerController(@PathVariable("id") long id) throws ResourceNotFoundException {
        customerService.deleteCustomer(id);
        return new ResponseEntity<String>("Customer deleteSuccessfully ", HttpStatus.OK);

    }
}
