package com.han.springdemo.system.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Han Yong
 * @since 2018-07-02
 */
@Data
@ConfigurationProperties
@Component
public class FileUploadProperties {
    private String location = "uploadDir";
    private String staticlocation = "E:/images/";
}
