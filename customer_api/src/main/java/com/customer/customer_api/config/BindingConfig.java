package com.customer.customer_api.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BindingConfig {
    
    @Bean
    public Binding bindingCustomerCheckQueue(Queue customerCheckQueue, TopicExchange customerExchange) {
        return BindingBuilder.bind(customerCheckQueue).to(customerExchange).with("check.customer");
    }
}
