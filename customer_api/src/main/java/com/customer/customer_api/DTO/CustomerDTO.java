package com.customer.customer_api.dto;

import java.util.UUID;

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

    public CustomerDTO(String name2, String email2, String cep2, String phone2) {
        this(UUID.randomUUID(), name2, email2, cep2, phone2);
    }
}

    