package com.togogotrain.moumoubicycle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.togogotrain.moumoubicycle.mappers")
public class MoumoubicycleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoumoubicycleApplication.class, args);
    }
}
