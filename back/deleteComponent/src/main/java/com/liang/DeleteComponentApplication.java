package com.liang;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liang.mapper")
public class DeleteComponentApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeleteComponentApplication.class, args);
    }

}
