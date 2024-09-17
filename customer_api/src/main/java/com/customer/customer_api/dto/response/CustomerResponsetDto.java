package com.customer.customer_api.dto.response;

import java.util.UUID;

public class CustomerResponsetDto {
    
    private UUID cutomerId;
    private String name;
    
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
    
}
