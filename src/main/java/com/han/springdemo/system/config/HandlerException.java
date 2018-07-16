package com.han.springdemo.system.config;

import com.han.springdemo.system.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerException {


    @ExceptionHandler(CustomException.class)
    public Map jsonException(CustomException c) {
        Map map = new HashMap();
        map.put("code", "204");
        map.put("message", c.getMessage());
        return map;
    }

    //   不建议使用
//    @ExceptionHandler(Exception.class)
//    public ModelAndView pageException(){
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("exception");
//        return mav;
//    }
}
