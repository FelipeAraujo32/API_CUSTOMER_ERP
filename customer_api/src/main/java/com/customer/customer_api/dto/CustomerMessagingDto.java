package com.customer.customer_api.dto;

import java.util.UUID;

public class CustomerMessagingDto {
    private UUID orderId;
    private UUID customerId;
    
    public UUID getOrderId() {
        return orderId;
    }
    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
    public UUID getCustomerId() {
        return customerId;
    }
    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    
}
