package com.customer.customer_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.customer.customer_api.entity.Customer;
import com.customer.customer_api.repository.CustomerRepository;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService service;

    @Mock
    private CustomerRepository repository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private Customer customer = new Customer("Felipe", "felipe@gmail.com", "71987987", "6199998888");

    @Test
    @DisplayName("")
    void testFindAllCustomer() {
    }
}
