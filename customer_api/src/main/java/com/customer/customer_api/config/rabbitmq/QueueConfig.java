package com.customer.customer_api.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    
    @Bean
    public Queue customerRequestQueue() {
        return new Queue("customer.request.queue", true);
    }

    @Bean
    public Queue customerResponseQueue() {
        return new Queue("customer.response.queue", true);
    }
}
