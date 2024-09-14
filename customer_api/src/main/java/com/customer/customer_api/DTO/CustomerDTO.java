package com.customer.customer_api.dto;

import java.util.Optional;
import java.util.UUID;

import com.customer.customer_api.entity.Customer;

public record CustomerDTO(

        UUID uuid,
        String name,
        String email,
        String cep,
        String phone) {

    public CustomerDTO(Customer customer) {
        this(
                customer.getUuid(),
                customer.getName(),
                customer.getEmail(),
                customer.getCep(),
                customer.getPhone());
    }

    public CustomerDTO(Optional<Customer> customerOptional) {
        this(customerOptional.orElse(null));
    }

    public Customer toCustomer() {
        Customer customer = new Customer();
        customer.setUuid(uuid);
        customer.setName(name);
        customer.setEmail(email);
        customer.setCep(cep);
        customer.setPhone(phone);
        return customer;
    }
}