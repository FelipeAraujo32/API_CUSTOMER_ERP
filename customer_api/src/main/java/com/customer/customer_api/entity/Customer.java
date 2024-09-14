package com.customer.customer_api.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity(name = "Customer")
@Table(name = "customer_erp")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    @NotNull(message = "Name cannot be Null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "The email cannot be blank")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "The email must be valid")
    private String email;

    @Column(name = "cep", nullable = false)
    @NotBlank(message = "The cep cannot be blank")
    @NotEmpty(message = "Cep cannot be empty")
    private String cep;

    @Column(name = "number_phone", nullable = false, unique = true)
    @NotBlank(message = "The number phone cannot be blank")
    @NotEmpty(message = "Number phone cannot be empty")
    private String phone;

    public Customer() {
    }

    public Customer(String name, String email, String cep, String phone) {
        this.name = name;
        this.email = email;
        this.cep = cep;
        this.phone = phone;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
