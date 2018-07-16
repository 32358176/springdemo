package com.han.springdemo.system.listener;

import com.han.springdemo.pojo.Student;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Han Yong
 * @since 2018-07-02
 */
@Component
public class SystemListener {
    /**
     * 监听用户登录事件,如果用户登录,
     * 处理对应的业务
     *
     * @param studentLoginEvent
     */
    @EventListener
    public void studentLoginListener(StudentLoginEvent studentLoginEvent) {
        Student student = studentLoginEvent.getStudent();
        System.out.println(student.toString());
    }
}
