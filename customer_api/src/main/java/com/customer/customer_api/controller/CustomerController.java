package com.customer.customer_api.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.customer_api.dto.CustomerDTO;
import com.customer.customer_api.entity.Customer;
import com.customer.customer_api.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    
    @Autowired
    private CustomerService service;


    @GetMapping("/api/v1/customers/{uuid}")
    public ResponseEntity<CustomerDTO> findCustomer(@PathVariable UUID uuid){
        Optional<Customer> findCustomer = service.findCustomer(uuid);

        CustomerDTO customerResponse = new CustomerDTO(
            findCustomer.get().getUuid(),  
            findCustomer.get().getName(), 
            findCustomer.get().getEmail(), 
            findCustomer.get().getCep(), 
            findCustomer.get().getPhone()
            );

        return ResponseEntity.ok().body(customerResponse);
    }

    @PostMapping("/api/v1/customers")
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        
        Customer createdCustomer = service.createCustomer(new CustomerDTO(
            customerDTO.name(),
            customerDTO.email(),
            customerDTO.cep(),
            customerDTO.phone()));

        CustomerDTO customerResponse = new CustomerDTO(
            createdCustomer.getUuid(), 
            createdCustomer.getName(),
            createdCustomer.getEmail(),
            createdCustomer.getCep(),
            createdCustomer.getPhone()
            );

        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
    }


    @PutMapping("/api/v1/customers/{uuid}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable UUID uuid, @Valid @RequestBody CustomerDTO customerDTO) throws Exception{

        Customer updateCustomer = service.updateCustomer(uuid, new CustomerDTO(
            customerDTO.name(), 
            customerDTO.email(), 
            customerDTO.email(), 
            customerDTO.phone()));

        CustomerDTO customerResponse = new CustomerDTO(
            updateCustomer.getUuid(), 
            updateCustomer.getName(), 
            updateCustomer.getEmail(), 
            updateCustomer.getCep(), 
            updateCustomer.getPhone()
            );
        
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @DeleteMapping("/api/v1/customers/{uuid}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID uuid){
        service.deleteCustomer(uuid);
        return ResponseEntity.noContent().build();
    }

}
