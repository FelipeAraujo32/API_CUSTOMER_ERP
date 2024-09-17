package com.customer.customer_api.service.business_exception;

public class CustomerNotFoundException extends BusinessException{
    
    public CustomerNotFoundException(String message){
        super(message);
    }
}
