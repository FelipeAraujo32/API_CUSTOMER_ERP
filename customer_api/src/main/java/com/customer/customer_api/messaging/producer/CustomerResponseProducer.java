package com.customer.customer_api.messaging.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.customer.customer_api.dto.CustomerErrorResponseDto;
import com.customer.customer_api.dto.CustomerMessagingDto;
import com.customer.customer_api.dto.response.CustomerResponsetDto;
import com.customer.customer_api.service.CustomerService;
import com.customer.customer_api.service.business_exception.CustomerNotFoundException;

@Component
public class CustomerResponseProducer {

    private final RabbitTemplate rabbitTemplate;
    private final CustomerService customerService;

    public CustomerResponseProducer(RabbitTemplate rabbitTemplate, CustomerService customerService) {
        this.rabbitTemplate = rabbitTemplate;
        this.customerService = customerService;
    }

    public void customerResponse(CustomerMessagingDto customerMessagingDto) {
        try {
            rabbitTemplate.convertAndSend(
                    "order.exchange",
                    "customer.service",
                    checkIftheCustomerExists(customerMessagingDto));
        } catch (CustomerNotFoundException ex) {
            rabbitTemplate.convertAndSend(
                    "order.exchange",
                    "customer.service.error",
                    notFoundCustomer(customerMessagingDto, ex));
        }
    }

    private CustomerErrorResponseDto notFoundCustomer(CustomerMessagingDto customerMessagingDto,
            CustomerNotFoundException ex) {
        CustomerErrorResponseDto customerError = new CustomerErrorResponseDto();
        customerError.setOrderId(customerMessagingDto.getOrderId());
        customerError.setErrorMessage(ex.getMessage());
        return customerError;
    }

    private CustomerMessagingDto checkIftheCustomerExists(CustomerMessagingDto customerMessagingDto) {
        CustomerResponsetDto customerResponsetDto = customerService.findByCustomer(customerMessagingDto.getCustomerId());
        CustomerMessagingDto customerResponse = new CustomerMessagingDto();
        customerResponse.setOrderId(customerMessagingDto.getOrderId());
        customerResponse.setCustomerId(customerResponsetDto.getCutomerId());
        return customerResponse;
    }

}
