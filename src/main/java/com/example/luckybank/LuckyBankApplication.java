package com.example.luckybank;

import com.example.luckybank.wow.Scheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LuckyBankApplication {



    public static void main(String[] args) {
        SpringApplication.run(LuckyBankApplication.class, args);

        Scheduler scheduler = new Scheduler(0, 1);

        // При необходимости остановите планировщик через некоторое время
        Runtime.getRuntime().addShutdownHook(new Thread(scheduler::shutdown));
    }

}
