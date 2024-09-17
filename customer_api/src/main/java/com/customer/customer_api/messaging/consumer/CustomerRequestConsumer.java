package com.customer.customer_api.messaging.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.customer.customer_api.dto.CustomerMessagingDto;
import com.customer.customer_api.messaging.producer.CustomerResponseProducer;

@Component
public class CustomerRequestConsumer {

    private final CustomerResponseProducer customerResponseProducer;

    public CustomerRequestConsumer(CustomerResponseProducer customerResponseProducer) {
        this.customerResponseProducer = customerResponseProducer;
    }

    @RabbitListener(queues = "customer.request.queue")
    public void handleCustomerRequest(CustomerMessagingDto customerMessagingDto) {
            customerResponseProducer.customerResponse(customerMessagingDto);
    }

}
