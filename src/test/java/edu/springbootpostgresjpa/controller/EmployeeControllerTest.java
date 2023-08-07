package edu.springbootpostgresjpa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.springbootpostgresjpa.exception.ResourceNotFoundException;
import edu.springbootpostgresjpa.model.Employee;
import edu.springbootpostgresjpa.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeService;


    private static String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return (objectMapper.writeValueAsString(obj));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void testSaveEmployeeController() throws Exception {
        Employee employee = new Employee(1L, "test", "test", "test");
        when(employeeService.saveEmployee(employee)).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employee)))
                .andExpect(status().isCreated());
        verify(employeeService).saveEmployee(employee);
    }

    @Test
    void testGetAllEmployeesController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(employeeService).getAllEmployees();
    }


    @Test
    void testGetEmployeeByIdController() throws Exception, ResourceNotFoundException {
        Employee employee = new Employee(1L, "test", "test", "test");
        when(employeeService.getEmployeeById(1L)).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(employeeService).getEmployeeById(1L);
    }

    @Test
    void testUpdateEmployeeController() throws Exception, ResourceNotFoundException {
        Employee employee = new Employee(1L, "test", "test", "test");
        when(employeeService.updateEmployee(employee, 1L)).thenReturn(employee);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employee)))
                .andExpect(status().isOk());
        verify(employeeService).updateEmployee(employee, 1L);
    }

    @Test
    void testDeleteEmployeeController() throws Exception, ResourceNotFoundException {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(employeeService).deleteEmployee(1L);
    }

}
