package edu.springbootpostgresjpa.service;

import edu.springbootpostgresjpa.exception.ResourceNotFoundException;
import edu.springbootpostgresjpa.model.Customer;
import edu.springbootpostgresjpa.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = Customer.builder()
                .customerName("test")
                .customerAddress("test")
                .customerEmail("test")
                .build();
        customerRepository.save(customer);
    }

    @Test
    void saveCustomerServiceTest() {
        // Arrange
        //Actual
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer saveCustomer = customerServiceImpl.saveCustomer(customer);
        //then
        Assertions.assertEquals(customer, saveCustomer);
    }

    @Test
    void getAllCustomersServiceTest() {
        when(customerRepository.findAll()).thenReturn(java.util.List.of(customer));
        java.util.List<Customer> customerList = customerServiceImpl.getAllCustomers();
        //then
        Assertions.assertEquals(java.util.List.of(customer), customerList);
    }

    @Test
    void getCustomerByIdServiceTest() throws ResourceNotFoundException {
        // Arrange
        //Actual
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customer));
        Customer customerById = customerServiceImpl.getCustomerById(1L);
        //then
        Assertions.assertEquals(customer, customerById);
    }

    @Test
    void getCustomerByIdDoesNotExist() {
        // Arrange
        //Actual
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        //then
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            customerServiceImpl.getCustomerById(1L);
        });
        Assertions.assertEquals(java.util.Optional.empty(), customerRepository.findById(1L));
    }

    @Test
    void updateCustomerServiceTest() throws ResourceNotFoundException {
        //Actual
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer updateCustomer = customerServiceImpl.updateCustomer(customer, 1L);
        //then
        Assertions.assertEquals(customer, updateCustomer);
    }

    @Test
    void updateCustomerDoesNotExist() {
        // Arrange
        //Actual
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        //then
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            customerServiceImpl.updateCustomer(customer, 1L);
        });
        Assertions.assertEquals(java.util.Optional.empty(), customerRepository.findById(1L));
    }

    @Test
    void deleteCustomerServiceTest() throws ResourceNotFoundException {
        //Actual
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customer));
        doNothing().when(customerRepository).deleteById(1L);
        customerServiceImpl.deleteCustomer(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteCustomerDoesNotExist() {
        // Arrange
        //Actual
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        //then
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            customerServiceImpl.deleteCustomer(1L);
        });
        verify(customerRepository, never()).deleteById(1L);
    }

}