package com.ewomail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ewomail.infrastructure.mapper")
public class EwomailApplication {
    public static void main(String[] args) {
        SpringApplication.run(EwomailApplication.class, args);
    }
} 