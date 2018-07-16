package com.han.springdemo.system.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Han Yong
 * @since 2018-06-29
 */

@Component
public class RabbitMqConfig {
    @RabbitListener(queues = "spring.rabbitmq.queue")
    public void queueListener(String message) {
        System.out.println(message + "----------------------");
    }
}
