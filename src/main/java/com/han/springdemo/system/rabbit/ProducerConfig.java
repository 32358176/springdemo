package com.han.springdemo.system.rabbit;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;

/**
 * @author Han Yong
 * @since 2018-06-29
 */
@Component
public class ProducerConfig {

    @Bean
    public RabbitMessagingTemplate getRabbitMessagingTemplate(RabbitTemplate rabbitTemplate) {
        RabbitMessagingTemplate rabbitMessagingTemplate =
                new RabbitMessagingTemplate();
        //指定json转换方式 ，使用默认的 fastjson.jar 包
        rabbitMessagingTemplate.setMessageConverter(new MappingJackson2MessageConverter());
        rabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
        return rabbitMessagingTemplate;
    }
}
