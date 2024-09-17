package com.customer.customer_api.config.rabbitmq;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeConfig {
    
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("order.exchange");
    }

}
