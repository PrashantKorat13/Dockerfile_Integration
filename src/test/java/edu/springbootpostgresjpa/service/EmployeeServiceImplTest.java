package edu.springbootpostgresjpa.service;


import edu.springbootpostgresjpa.exception.ResourceNotFoundException;
import edu.springbootpostgresjpa.model.Employee;
import edu.springbootpostgresjpa.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Mock
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
    void saveEmployeeServiceTest() {
        // Arrange
        //Actual
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee saveEmployee = employeeServiceImpl.saveEmployee(employee);
        //then
        Assertions.assertEquals(employee, saveEmployee);
    }

    @Test
    void getAllEmployeesServiceTest() {
        when(employeeRepository.findAll()).thenReturn(java.util.List.of(employee));
        java.util.List<Employee> employeeList = employeeServiceImpl.getAllEmployees();
        //then
        Assertions.assertEquals(java.util.List.of(employee), employeeList);
    }

    @Test
    void getEmployeeByIdServiceTest() throws ResourceNotFoundException {
        // Arrange
        //Actual
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employee));
        Employee employeeById = employeeServiceImpl.getEmployeeById(1L);
        //then
        Assertions.assertEquals(employee, employeeById);
    }


    @Test
    void getCustomerByIdDoesNotExist() {
        // Arrange
        //Actual
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        //then
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            employeeServiceImpl.getEmployeeById(1L);
        });
        Assertions.assertEquals(java.util.Optional.empty(), employeeRepository.findById(1L));
    }


    @Test
    void updateEmployeeServiceTest() throws ResourceNotFoundException {
        //Actual
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee updateEmployee = employeeServiceImpl.updateEmployee(employee, 1L);
        //then
        Assertions.assertEquals(employee, updateEmployee);
    }


    @Test
    void deleteEmployeeServiceTest() throws ResourceNotFoundException {
        //Actual
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employee));
        doNothing().when(employeeRepository).deleteById(1L);
        employeeServiceImpl.deleteEmployee(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }

}
