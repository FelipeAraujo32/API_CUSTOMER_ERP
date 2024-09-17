package com.customer.customer_api.dto;
import java.util.UUID;

public class CustomerErrorResponseDto {
    private UUID orderId;
    private String errorMessage;
    
    public UUID getOrderId() {
        return orderId;
    }
    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    
}
