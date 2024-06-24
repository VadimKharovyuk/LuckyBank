package com.example.luckybank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LuckyBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyBankApplication.class, args);
    }

}
