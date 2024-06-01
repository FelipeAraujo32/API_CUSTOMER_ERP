package com.customer.customer_api.domain;

import java.util.UUID;

public class Customer {
    
    private UUID uuid;

    private String name;

    private String email;

    private String cep;

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
