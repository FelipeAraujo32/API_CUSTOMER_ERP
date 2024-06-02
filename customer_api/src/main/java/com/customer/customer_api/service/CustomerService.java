package com.customer.customer_api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.customer_api.dto.CustomerDTO;
import com.customer.customer_api.entity.Customer;
import com.customer.customer_api.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Optional<Customer> findCustomer(UUID uuid) {
        return repository.findById(uuid);
    }

    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer createCustomer = new Customer(
            customerDTO.name(), 
            customerDTO.email(), 
            customerDTO.cep(), 
            customerDTO.phone());
            
        return repository.save(createCustomer);
    }

    public Customer updateCustomer(UUID uuid, CustomerDTO customerDTO) throws Exception{
        
        Optional<Customer> optCustomer = repository.findById(uuid);
        if(optCustomer.isEmpty()){
            throw new Exception ("Participant not found");
        }
        
        Customer updateCustomer = optCustomer.get();
        updateCustomer.setName(customerDTO.name());
        updateCustomer.setEmail(customerDTO.email());
        updateCustomer.setCep(customerDTO.cep());
        updateCustomer.setPhone(customerDTO.phone());
        
        return repository.save(updateCustomer);
        
    }

    public void deleteCustomer(UUID uuid){
        repository.deleteById(uuid);
    }
}