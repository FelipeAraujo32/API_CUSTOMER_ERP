package com.customer.customer_api.messaging.consumer;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.customer.customer_api.service.CustomerService;

@Component
public class CustomerRequestConsumer {

    private final CustomerService customerService;

    public CustomerRequestConsumer(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RabbitListener(queues = "customer.check.queue")
    public Boolean handleCustomerCkeck(UUID customerUuid) {
        try {
            customerService.findByCustomer(customerUuid);
            return true;
        } catch (NoSuchElementException | IllegalArgumentException e) {
            return false;
        }
    }

}
