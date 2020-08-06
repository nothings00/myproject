package com.znothings.test;

import org.springframework.util.SystemPropertyUtils;

import java.util.Arrays;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/7/17 4:42 PM
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        Arrays.stream(System.getProperties().toString().split(","))
                .forEach(s -> System.out.println(s));
        System.out.println(System.getProperties().getProperty("user.dir"));
    }
}
