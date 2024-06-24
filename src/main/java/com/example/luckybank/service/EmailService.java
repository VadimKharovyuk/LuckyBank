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
        // Отправляем сообщение в welcomeExchange
    }


    public void sendMassEmail(String subject, String text, List<Client> clients) {
        for (Client client : clients) {
            sendEmail(client.getEmail(), subject, text);
            rabbitTemplate.convertAndSend("emailQueue", new EmailRequest(client.getEmail(), subject, text));
        }
    }




//public void sendMassEmail(String subject, List<Client> clients) {
//    for (Client client : clients) {
//        String text = "Уважаемый(ая) " + client.getName() + " " + client.getLastName() + ",\n\n" +
//                "Мы хотим выразить нашу искреннюю благодарность за то, что вы выбрали наш банк и пользуетесь нашими услугами.\n" +
//                "Для нас большая честь быть вашим финансовым партнёром.\n\n" +
//                "В качестве благодарности мы рады предложить вам эксклюзивные скидки на наши услуги.\n" +
//                "Пожалуйста, свяжитесь с нашей службой поддержки, чтобы узнать больше о доступных скидках и специальных предложениях.\n\n" +
//                "С наилучшими пожеланиями,\n" +
//                "Команда " + "Название вашего банка" + "\n" +
//                "Телефон: +123456789\n" +
//                "Email: info@examplebank.com";
//
//        sendEmail(client.getEmail(), subject, text);
//    }
//}



}

