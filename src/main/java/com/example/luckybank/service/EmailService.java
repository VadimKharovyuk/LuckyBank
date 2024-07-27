package com.example.luckybank.service;

import com.example.luckybank.model.Client;
import com.example.luckybank.pojo.EmailRequest;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailService {

    private  final  JavaMailSender mailSender;
    private final RabbitTemplate rabbitTemplate;


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
    }


public void sendMassEmail(String subject, String text, List<Client> clients) {
    for (Client client : clients) {
        sendEmail(client.getEmail(), subject, text);
        rabbitTemplate.convertAndSend("emailExchange", "", new EmailRequest(client.getEmail(), subject, text));
    }
}

    public void sendPaymentNotification(String to, double amount, Client client) {
        String subject = "Подтверждение платежа";
        String text = String.format("Уважаемый(ая) %s,\n\n" +
                "Ваш платеж на сумму UAH %.2f был успешно обработан.\n" +
                "Спасибо за использование услуг нашего банка.\n\n" +
                "С уважением,\n" +
                "Ваш Lucky Bank", client.getName(), amount);

        sendEmail(to, subject, text);
    }








}

