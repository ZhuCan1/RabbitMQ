package com.zc.config;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue Queue1() {
        return new Queue("TestQueue1");
    }

    @Bean
    public Queue Queue2() {
        return new Queue("TestQueue2");
    }

    @Bean
    public Queue QueueUser() {
        return new Queue("TestQueueUser");
    }
}
