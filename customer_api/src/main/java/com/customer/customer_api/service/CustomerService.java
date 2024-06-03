package com.customer.customer_api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.customer_api.entity.Customer;
import com.customer.customer_api.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> findAllCustomer(){
        return repository.findAll();
    }

    public Optional<Customer> findCustomer(UUID uuid) {
        return repository.findById(uuid);
    }

    public Customer createCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Customer updateCustomer(UUID uuid, Customer customer) throws Exception{
        
        Optional<Customer> optCustomer = repository.findById(uuid);
        if(optCustomer.isEmpty()){
            throw new Exception ("Participant not found");
        }
        return repository.save(customer);
        
    }

    public void deleteCustomer(UUID uuid){
        repository.deleteById(uuid);
    }
}