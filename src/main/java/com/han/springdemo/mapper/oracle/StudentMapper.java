package com.han.springdemo.mapper.oracle;

import com.han.springdemo.pojo.Student;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author han
 * @since 2018-06-29
 */
@Repository
public interface StudentMapper {

    Student selectById(Serializable id);
}
