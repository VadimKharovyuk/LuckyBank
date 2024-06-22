//package com.example.luckybank.service;
//
//import com.example.luckybank.model.Client;
//import com.example.luckybank.service.EmailService;
//import lombok.AllArgsConstructor;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class WelcomeReceiver {
//
//    private final EmailService emailService;
//
//
//
//    @RabbitListener(queues = "welcomeQueue")
//    public void receiveWelcomeMessage(Client client) {
//        // Пример: теперь мы можем использовать client для отправки персонализированного сообщения
//        emailService.sendWelcomeEmail(client);
//    }
//}
