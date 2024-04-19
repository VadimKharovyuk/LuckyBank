package com.example.luckybank.controller;

import com.example.luckybank.model.Client;
import com.example.luckybank.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {

    private final ClientService clientService;

    @GetMapping
    public String showRegistrationForm() {
        return "registration"; // Имя представления (шаблона) для отображения формы регистрации
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

        // Сохраняем клиента в банке
        clientService.createClient(newClient);

        // Перенаправляем пользователя на другую страницу после успешной регистрации
        return "redirect:/";
    }
}
