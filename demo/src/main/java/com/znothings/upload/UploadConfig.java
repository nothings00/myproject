package com.znothings.upload;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * 上传文件配置
 * @author zenghh, 625111833@qq.com
 * @date 2019-06-13 17:18
 * @version 1.0.0
 */
@Configuration
public class UploadConfig {
    /**
     * 设置临时存储文件路径
     * @return
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String root = path.substring(path.indexOf("/"),path.indexOf("/",path.indexOf("/")+1));
        factory.setLocation(root+"/upload/tmp");
        return factory.createMultipartConfig();
    }
}
