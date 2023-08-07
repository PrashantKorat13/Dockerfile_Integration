package edu.springbootpostgresjpa.service;

import edu.springbootpostgresjpa.exception.ResourceNotFoundException;
import edu.springbootpostgresjpa.model.Customer;
import edu.springbootpostgresjpa.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;


    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super();
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(long id) throws ResourceNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new ResourceNotFoundException("Customer", "id", id);
        }
    }

    @Override
    public Customer updateCustomer(Customer customer, long id) throws ResourceNotFoundException {
        return customerRepository.findById(id).map(
                existingCustomer -> {
                    existingCustomer.setCustomerName(customer.getCustomerName());
                    existingCustomer.setCustomerAddress(customer.getCustomerAddress());
                    existingCustomer.setCustomerEmail(customer.getCustomerEmail());
                    return customerRepository.save(existingCustomer);
                }
        ).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "id", id)
        );
    }

    @Override
    public void deleteCustomer(long id) throws ResourceNotFoundException {
        customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "id", id)
        );
        customerRepository.deleteById(id);
        customerRepository.findById(id).orElse(null);

    }
}
