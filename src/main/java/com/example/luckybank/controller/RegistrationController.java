package com.example.luckybank.controller;

import com.example.luckybank.model.Client;
import com.example.luckybank.service.ClientService;
import com.example.luckybank.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {

    private final ClientService clientService;
    private final EmailService emailService ;
    private final RabbitTemplate rabbitTemplate;


    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }


    @PostMapping
    public String processRegistrationForm(@RequestParam String name,
                                          @RequestParam String lastName,
                                          @RequestParam String address,
                                          @RequestParam String email) {
        // Создаем нового клиента на основе данных из формы
        Client newClient = new Client();
        newClient.setName(name);
        newClient.setLastName(lastName);
        newClient.setAddress(address);
        newClient.setEmail(email);

        // Сохраняем клиента в базе данных
        Client savedClient = clientService.createClient(newClient);

        // Отправляем приветственное письмо в RabbitMQ
        emailService.sendWelcomeEmail(savedClient);
        rabbitTemplate.convertAndSend("welcomeExchange", "", newClient);

        return "redirect:/";
    }


}
