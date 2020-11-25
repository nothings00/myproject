package com.znothings.thread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLClassLoader;

/**
 *
 * @author zenghh
 * @date 2020/11/24 3:55 PM
 * @version 1.0
 */
@RestController
@EnableAsync
@EnableCaching
@SpringBootApplication
public class ClassLoadContextTest {
    public static void main(String[] args) {
        SpringApplication.run(ClassLoadContextTest.class,args);
    }

    @RequestMapping("/clazz")
    public String clazz(){
        DOMUtils.create();
        return Thread.currentThread().toString();
    }
}
