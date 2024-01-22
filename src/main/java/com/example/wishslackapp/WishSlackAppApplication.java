package com.example.wishslackapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ServletComponentScan
@EnableFeignClients
public class WishSlackAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WishSlackAppApplication.class, args);
    }

}
