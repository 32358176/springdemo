package com.han.springdemo.view;

import com.han.springdemo.pojo.Page;
import com.han.springdemo.system.exception.CustomException;
import com.han.springdemo.system.file.FileSystemStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Han Yong
 * @since 2018-07-02
 */
@RestController
@CrossOrigin
@RequestMapping("/file")
@Api(value = "文件上传下载功能", tags = "文件上传下载")
public class FileController {

    @Autowired
    FileSystemStorageService fileSystemStorageService;

    @ApiOperation(value = "文件上传接口", notes = "用户需要提供一个name=file的字节流文件类型")
    @ApiImplicitParams({@ApiImplicitParam(name = "file", value = "上传文件")})
    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) throws CustomException {
        Page page = fileSystemStorageService.store(multipartFile);
        return page;
    }

    /**
     * 下载文件的接口,与SSM也没有区别,需要注意的是,
     * 这既是一个下载接口,也是一个展示图片的接口(只适用于图片与视频),
     * 如果你的请求地址为 : <a  ="/demo/download?filename=2018-06-30/dd9cca9215154be0a5ce4045f74a1f97.PNG">下载</a>,
     * 那么就会提供下载功能, 如果地址为 : <img src="/demo/download?filename=2018-06-30/dd9cca9215154be0a5ce4045f74a1f97.PNG">,
     * 他就会自动展示图片(实际中应用非常广泛,这样做可以避免暴露服务器真实地址),
     * 当然使用传统的通过虚拟路径的方式也可以访问到服务器图片,
     * 但是因为springboot内置tomact,所以我们需要在配置文件中配置虚拟路径,
     * 并且在拦截器中提供相应配置 见 : HandleConfig
     *
     * @param filename
     * @return
     */

    @ApiOperation(value = "文件下载接口", notes = "用户需要提供一个文件名")
    @ApiImplicitParams({@ApiImplicitParam(name = "file", value = "下载文件")})
    @GetMapping("/download")
    public ResponseEntity<Resource> download(String filename) throws UnsupportedEncodingException {
        Resource resource = null;

        try {
            resource = fileSystemStorageService.loadAsResource(filename);
        } catch (CustomException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(filename, "utf-8") + "\"")
                .body(resource);
    }
}
