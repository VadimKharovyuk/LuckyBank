package com.example.luckybank.сonfiguration;

import com.example.luckybank.service.EmailMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class EmailProducer {
    private final  RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEmailMessage(EmailMessage emailMessage) {


        rabbitTemplate.convertAndSend(exchange, "", emailMessage);
    }
}
