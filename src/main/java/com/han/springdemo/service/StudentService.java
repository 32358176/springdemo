package com.han.springdemo.service;

import com.han.springdemo.pojo.Student;
import com.han.springdemo.mapper.oracle.StudentMapper;
import com.han.springdemo.system.listener.StudentLoginEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author han
 * @since 2018-06-29
 */
@Service
@CacheConfig(cacheNames = "com.han.springdemo.service.impl")
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 事件的发布者,springboot内置
     */
    @Autowired
    ApplicationContext applicationContext;

    @Cacheable(key = "'selectById_'.concat(#a0)")
    public Student selectById(Serializable id) {
        return studentMapper.selectById(id);
    }

    /**
     * 该方法用来模拟用户登录,
     * 在用户登录之后,可以有选择的把一些业务交给监听器处理,
     * 完成业务的解耦,让这个业务系统不会变的特别繁琐,繁杂,难以维护,更好的解耦,
     * 所以,在这里用户登录之后,完成事件的发布
     *
     * @param id
     * @return
     */

    public Student queryStudentById(Integer id) {
        Student student = studentMapper.selectById(id);
        /**
         * 如果student!=null,说明用户已经
         * 成功登录,那么我们在这里发布时间
         */
        if (student != null) {
            applicationContext.publishEvent(new StudentLoginEvent(this, student));
        }
        return student;
    }
}
