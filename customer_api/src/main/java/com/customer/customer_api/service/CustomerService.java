package com.customer.customer_api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.customer_api.entity.Customer;
import com.customer.customer_api.repository.CustomerRepository;
import com.customer.customer_api.service.business_exception.NotFoundException;

import jakarta.transaction.Transactional;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ValidationDataService dataProduct;


    public List<Customer> findAllCustomer() {
        var customers = repository.findAll();
        if (customers.isEmpty()) {
            throw new NotFoundException("Customer list not found");
        }
        return customers;
    }

    public Customer findByCustomer(UUID uuid) {
        return repository.findById(uuid).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Customer createCustomer(Customer createCustomer) {
        // Validator setting in an external class
        dataProduct.validation(createCustomer);
        var customer = repository.save(createCustomer);
        return customer;
    }

    public Customer updateCustomer(UUID uuid, Customer customer) throws Exception {

        Customer customerDb = this.findByCustomer(uuid);
        if (!customerDb.getUuid().equals(uuid)){
            throw new NotFoundException("The UUID must be the same as the one you want to update");
        }
        // Validator setting in an external class
        dataProduct.validation(customer);
        customer.setUuid(uuid);
        return repository.save(customer);
    }

    public void deleteCustomer(UUID uuid) {
        Customer customerDb = this.findByCustomer(uuid);
        this.repository.delete(customerDb);
    }
}