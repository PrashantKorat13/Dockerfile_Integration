package edu.springbootpostgresjpa.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.springbootpostgresjpa.exception.ResourceNotFoundException;
import edu.springbootpostgresjpa.model.Customer;
import edu.springbootpostgresjpa.service.CustomerServiceImpl;
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

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerService;


    private static String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return (objectMapper.writeValueAsString(obj));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSaveCustomerController() throws Exception {
        Customer customer = new Customer(1L, "test", "test", "test");
        when(customerService.saveCustomer(customer)).thenReturn(customer);
        mockMvc.perform(MockMvcRequestBuilders.post("/gov/customer/saveCustomer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer)))
                .andExpect(status().isCreated());
        verify(customerService).saveCustomer(customer);
    }

    @Test
    void testGetAllCustomersController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/gov/customer/getAllCustomers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(customerService).getAllCustomers();
    }

    @Test
    void testGetCustomerByIdController() throws Exception, ResourceNotFoundException {
        Customer customer = new Customer(1L, "test", "test", "test");
        when(customerService.getCustomerById(1L)).thenReturn(customer);
        mockMvc.perform(MockMvcRequestBuilders.get("/gov/customer/getCustomerById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(customerService).getCustomerById(1L);
    }

    @Test
    void testUpdateCustomerController() throws Exception, ResourceNotFoundException {
        Customer customer = new Customer(1L, "test", "test", "test");
        when(customerService.updateCustomer(customer, 1L)).thenReturn(customer);
        mockMvc.perform(MockMvcRequestBuilders.put("/gov/customer/updateCustomer/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer)))
                .andExpect(status().isOk());
        verify(customerService).updateCustomer(customer, 1L);
    }

    @Test
    void testDeleteCustomerController() throws Exception, ResourceNotFoundException {
        mockMvc.perform(MockMvcRequestBuilders.delete("/gov/customer/deleteCustomer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(customerService).deleteCustomer(1L);
    }

}