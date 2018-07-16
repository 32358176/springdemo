package com.han.springdemo.view;

import com.han.springdemo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/getUser")
    public Map getUser() {
        return usersService.selectByPrimaryKey(4);
    }
}
