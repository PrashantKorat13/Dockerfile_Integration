package edu.springbootpostgresjpa.repository;

import edu.springbootpostgresjpa.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
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
    @DisplayName("JUnit test for save Customer operation")
    void saveCustomerTest() {
        customerRepository.save(customer);
        //then
        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isGreaterThan(0);
    }

    @Test
    void findAllCustomerTest() {
        Customer customer = Customer.builder()
                .customerName("test1")
                .customerAddress("test1")
                .customerEmail("test1")
                .build();
        customerRepository.save(customer);
        List<Customer> customerList = customerRepository.findAll();
        //then
        assertThat(customerList).isNotNull();
        assertThat(customerList.size()).isEqualTo(2);

    }

    @Test
    void findCustomerByIdTest() {

        customerRepository.save(customer);
        Customer customer1 = customerRepository.findById(customer.getId()).orElse(null);
        //then
        assertThat(customer1).isNotNull();
        assertThat(customer1.getId()).isEqualTo(customer.getId());

    }

    @Test
    void deleteCustomerByIdTest() {
        customerRepository.deleteById(customer.getId());
        Customer customer1 = customerRepository.findById(customer.getId()).orElse(null);
        //then
        assertThat(customer1).isNull();

    }
}


