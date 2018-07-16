package com.han.springdemo.system.config;

import com.han.springdemo.system.handler.EncodingHandler;
import com.han.springdemo.system.handler.LoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class HandlerConfig extends WebMvcConfigurationSupport {

    /**
     * 注入要加载的拦截器
     */
    @Autowired
    private EncodingHandler encodingHandler;

    @Value("${file.upload}")
    private String imageUrl;
    @Autowired
    private LoginHandler loginHandler;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(encodingHandler);
        registry.addInterceptor(loginHandler);
        super.addInterceptors(registry);
    }

    /**
     * 配置静态资源目录
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 虚拟路径
        registry.addResourceHandler("/images/**")
                // 本地路径
                .addResourceLocations(imageUrl);

        // 将static下的所有文件设为静态,可以直接访问
        registry.addResourceHandler("/swagger/**")
                .addResourceLocations("classpath:static/dist/");

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:templates/");
        super.addResourceHandlers(registry);
    }

    /**
     * 配置首页
     *
     * @param registry
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        //直接访问，转发到index
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
}
