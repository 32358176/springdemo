package com.han.springdemo.system.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 调用第三方接口
 *
 * @author Han Yong
 * @since 2018-07-02
 */
@Configuration
public class RestfulConfig {
    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
