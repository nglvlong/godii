package com.fpt.godii.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
public class GodiiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GodiiApplication.class, args);
    }

}
