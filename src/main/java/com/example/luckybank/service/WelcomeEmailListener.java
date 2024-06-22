package com.example.luckybank.service;

import com.example.luckybank.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WelcomeEmailListener {
    private final EmailService emailService;
    private final RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = "welcomeQueue")
    public void receiveWelcomeMessage(Client client) {
        // Логика для обработки приветственного сообщения
        emailService.sendWelcomeEmail(client);
        rabbitTemplate.convertAndSend("welcomeExchange", "", client);
    }
}
