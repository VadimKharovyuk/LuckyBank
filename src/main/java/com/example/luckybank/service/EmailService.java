package com.example.luckybank.service;

import com.example.luckybank.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private  final  JavaMailSender mailSender;


    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendWelcomeEmail(Client client) {
        String subject = "Добро пожаловать в наш банк!";
        String text = "Уважаемый(ая) " + client.getName() + " " + client.getLastName() + ",\n\n" +
                "Мы рады приветствовать вас в нашем банке! Спасибо за регистрацию.\n" +
                "Вы стали частью нашего сообщества, где мы стремимся предоставлять высококачественные финансовые услуги и поддержку.\n\n" +
                "Если у вас возникнут вопросы или вам потребуется помощь, пожалуйста, не стесняйтесь обращаться к нашей службе поддержки.\n\n" +
                "С наилучшими пожеланиями,\n" +
                "Команда " + "Название вашего банка" + "\n" +
                "Телефон: +123456789\n" +
                "Email: info@examplebank.com";

        sendEmail(client.getEmail(), subject, text);
        // Отправляем сообщение в welcomeExchange
    }

}

