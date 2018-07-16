package com.han.springdemo.system.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Han Yong
 * @since 2018-06-29
 */

@Component
public class ExchangeConfig {
    // 队列名称
    @Value("${spring.rabbitmq.queue}")
    private String queueName;
    // 交换机名称
    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName;

    /**
     * mq 管理对象
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitAdmin getRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    /**
     * 获取队列
     *
     * @param rabbitAdmin
     * @return
     */
    @Bean(name = "queue_1")
    public Queue getQueue(RabbitAdmin rabbitAdmin) {
        // 设置队列名称
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    /**
     * 获取交换机
     *
     * @param rabbitAdmin
     * @return
     */
    @Bean
    public TopicExchange getTopicExchange(RabbitAdmin rabbitAdmin) {
        // 设置交换机名称 。持久化 ， 不自动删除
        TopicExchange topicExchange =
                new TopicExchange(exchangeName, true, false);
        rabbitAdmin.declareExchange(topicExchange);
        return topicExchange;
    }

    /**
     * 绑定队列到交换机
     *
     * @param queue
     * @param topicExchange
     * @return
     */

    @Bean
    public Binding getBinding(@Qualifier("queue_1") Queue queue, TopicExchange topicExchange) {
        /**
         *  1.队列名称
         *  2.队列
         *  3.交换机名称
         *  4.路由地址
         *  5.null
         */
        return new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, queueName, null);
    }
}
