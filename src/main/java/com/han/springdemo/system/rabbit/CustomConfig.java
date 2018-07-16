package com.han.springdemo.system.rabbit;

import com.han.springdemo.pojo.Student;
import com.han.springdemo.view.WebSocket;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Han Yong
 * @since 2018-06-29
 */

@Component
public class CustomConfig {
    /**
     * 监听名为  spring.rabbitmq.queue 的队列
     *
     * @param student 根据传入正文设置类型
     */
    @RabbitListener(queues = "spring.rabbitmq.queue")
    public void customer(Student student) {
        for (WebSocket w : WebSocket.webSockets) {
            try {
                w.sendMessage(student.toString());
            } catch (IOException e) {
                continue;
            }
        }
    }
}
