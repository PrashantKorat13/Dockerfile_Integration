package edu.springbootpostgresjpa.repository;

import edu.springbootpostgresjpa.model.Employee;
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
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1)
                .firstName("test")
                .lastName("test")
                .email("test")
                .build();
        employeeRepository.save(employee);
    }

    @Test
    @DisplayName("JUnit test for save Employee operation")
    void saveEmployeeTest() {
        employeeRepository.save(employee);
        //then
        assertThat(employee).isNotNull();
        assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    void findAllEmployeeTest() {
        Employee employee = Employee.builder()
                .id(3)
                .firstName("test1")
                .lastName("test1")
                .email("test1")
                .build();
        employeeRepository.save(employee);
        List<Employee> employeeList = employeeRepository.findAll();
        //then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }

    @Test
    void findEmployeeByIdTest() {

        employeeRepository.save(employee);
        Employee employee1 = employeeRepository.findById(employee.getId()).orElse(null);
        //then
        assertThat(employee1).isNotNull();
        assertThat(employee1.getId()).isEqualTo(employee.getId());

    }

    @Test
    void deleteEmployeeByIdTest() {
        employeeRepository.deleteById(employee.getId());
        Employee employee1 = employeeRepository.findById(employee.getId()).orElse(null);
        //then
        assertThat(employee1).isNull();

    }

}


