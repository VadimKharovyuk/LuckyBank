package com.example.luckybank.listener;

import com.example.luckybank.pojo.EmailRequest;
import com.example.luckybank.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailListener {

    private final EmailService emailService;

    @RabbitListener(queues = "emailQueue")
    public void processEmailQueue(EmailRequest emailRequest) {
        System.out.println("Отработала первая emailQueue для розслыки  ");
        emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getText());
    }
    @RabbitListener(queues = "emailQueue")
    public void processEmailQueue1(EmailRequest emailRequest) {
        System.out.println("Отработала вторая emailQueue для розслыки  ");
        emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getText());
    }
}
