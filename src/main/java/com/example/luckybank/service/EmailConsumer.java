package com.example.luckybank.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailConsumer {
    private final JavaMailSender javaMailSender;

    @RabbitListener(queues = "transferQueue")
    public void receiveMessage(EmailMessage emailMessage) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(emailMessage.getRecipientEmail());
            helper.setSubject(emailMessage.getSubject());
            helper.setText(emailMessage.getBody());

            javaMailSender.send(message);
            System.out.println("Очередь регистираци отработала -transferQueue, ");
        } catch (MessagingException | MailException e) {
            e.printStackTrace();
        }
    }
}
