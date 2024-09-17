package com.customer.customer_api.dto.request;

import java.util.UUID;

public class CustomerRequestDto {

    private UUID cutomerId;
    private String name;
    private String email;
    private String cep;
    private String phone;

    public UUID getCutomerId() {
        return cutomerId;
    }

    public void setCutomerId(UUID cutomerId) {
        this.cutomerId = cutomerId;
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