package com.example.luckybank.сonfiguration;//package com.example.luckybank.сonfiguration;

import com.example.luckybank.service.EmailMessage;
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
        System.out.println("Очередь  отправки сообщений отработала " +emailMessage.getRecipientEmail());


        rabbitTemplate.convertAndSend(exchange, "", emailMessage);
    }
}




