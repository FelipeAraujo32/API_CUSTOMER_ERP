package com.customer.customer_api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.customer_api.DTO.CustomerDTO;
import com.customer.customer_api.entity.Customer;
import com.customer.customer_api.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Optional<Customer> findCustomer(UUID uuid) {
        return repository.findById(uuid);
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        return repository.save(customerDTO);
    }

}
