package com.example.luckybank.listener;

import com.example.luckybank.model.Client;
import com.example.luckybank.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WelcomeEmailListener {
    private final EmailService emailService;


    @RabbitListener(queues = "welcomeQueue")
    public void receiveWelcomeMessage(Client client) {
        System.out.println("Очередь 1 welcomeQueue " +client.getName());
        emailService.sendWelcomeEmail(client);

    }
    @RabbitListener(queues = "welcomeQueue")
    public void receiveWelcomeMessage1(Client client) {
        System.out.println("Очередь 2 welcomeQueue " +client.getEmail());
//        emailService.sendWelcomeEmail(client);

    }
}
