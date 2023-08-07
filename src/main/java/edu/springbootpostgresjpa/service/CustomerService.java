package edu.springbootpostgresjpa.service;

import edu.springbootpostgresjpa.exception.ResourceNotFoundException;
import edu.springbootpostgresjpa.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerById(long id) throws ResourceNotFoundException;

    Customer updateCustomer(Customer customer, long id) throws ResourceNotFoundException;

    void deleteCustomer(long id) throws ResourceNotFoundException;
}
