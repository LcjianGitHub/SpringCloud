package com.example.demo0928;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.demo0928.mapper")
public class Demo0928Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo0928Application.class, args);
    }

}
