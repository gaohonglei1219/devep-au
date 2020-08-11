package com.devep;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.devep.mapper")
public class DevepAuApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevepAuApplication.class, args);
    }

}
