package com.han.springdemo.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @author Han Yong
 * @since 2018-07-02
 */
@CrossOrigin
@RestController
@RequestMapping("/rest")
public class ResterController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * restTemplate 支持我们常用的请求类型,
     * 比如 delete()/put(),但是通常情况下,
     * 第三方接口是不会提供删除/添加功能的(除了我们自己系统内部间的调用),
     * 所以一般只使用 get/post 请求, 根据返回内容的不同,
     * rest 提供了以下几个方法 :
     * 1.  getForEntity / postForEntity
     * 2.  getForObject / postForObject
     * entity类的方法包含一个完整的请求头信息,比如浏览器信息,状态码,信息正文...
     * object类的方法只包含一个信息正文,在请求时可以提供一个信息正文的类型,
     * rest 将自动装箱,如果需要传入参数,则需要提供一个map集合,将参数装入map
     * 进行传输(不建议直接写到url当中,比如 http://www.baidu.com?name=tom&pass=123 );
     *
     * @return
     */

    @GetMapping("/entity")
    public Object restfulEntity() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", "tom");
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("http://gturnquist-quoters.cfapps.io/api/random", String.class, hashMap);
        responseEntity.getStatusCode();
        responseEntity.getHeaders();
        String string = responseEntity.getBody();
        return string;
    }
}
