package com.han.springdemo.service;


import com.han.springdemo.mapper.mysql.UsersMapper;


import com.han.springdemo.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import java.util.Map;

@Service
@CacheConfig(cacheNames = "com.han.springdemo.service")
public class UsersService {

    @Autowired
    private UsersMapper usersMapper;


    /**
     * 根据用户名查询用户信息
     *
     * @param id
     * @return 根据用户名查询用户信息返回信息
     */

    @Cacheable(key = "'selectByPrimaryKey_'.concat(#a0)")
    public Map selectByPrimaryKey(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Users user = usersMapper.selectByPrimaryKey(id);
        if (user != null) {
            map.put("resultCode", "200");
            map.put("resultMsg", "查询成功");
            map.put("resultUser", user);
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "查询失败");
        }
        return map;
    }


}
