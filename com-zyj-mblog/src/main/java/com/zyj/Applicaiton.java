package com.zyj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: ZhangYajie
 * @Description:
 * @Date: Created in 17:01 2018/6/12
 */


@SpringBootApplication //Spring Boot核心注解，用于开启自动配置
@MapperScan("com.zyj.dao")//将项目中对应的mapper类的路径加进来就可以了
public class Applicaiton {

    public static void main(String[] args) {
        SpringApplication.run(Applicaiton.class, args);
    }
}
