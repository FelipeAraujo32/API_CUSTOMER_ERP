package com.customer.customer_api.dto;

import java.util.Optional;
import java.util.UUID;

import com.customer.customer_api.entity.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerDTO(
    
    UUID uuid, 
    @NotBlank(message = "The name cannot be blank") 
    String name, 
    @NotBlank(message = "The email cannot be blank") 
    @Email(message = "The email must be valid")
    String email, 
    @NotBlank(message = "The cep cannot be blank")
    String cep,
    @NotBlank(message = "The phone cannot be blank")
    String phone
    ){

    public CustomerDTO (Customer customer){
        this(
            customer.getUuid(), 
            customer.getName(), 
            customer.getEmail(), 
            customer.getCep(), 
            customer.getPhone()
        );
    }

    public CustomerDTO (Optional<Customer> customerOptional){
        this(customerOptional.orElse(null));
    }
    
    public Customer toCustomer(){
        Customer customer = new Customer();
        customer.setUuid(uuid);
        customer.setName(name);
        customer.setEmail(email);
        customer.setCep(cep);
        customer.setPhone(phone);
        return customer;
    }
}

    