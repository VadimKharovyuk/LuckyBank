package com.example.luckybank.controller;

import com.example.luckybank.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class BankController {
    private final ClientService clientService;
    @GetMapping("/")
    public String mainPage(Model model) {

        model.addAttribute("pageTitle", "Добро пожаловать в Lucky Bank!");
        model.addAttribute("welcomeMessage", "Добро пожаловать в наш банк. Здесь вы можете отправлять и получать деньги, управлять своими карточками и многое другое.");
        return "main"; // Возвращает имя шаблона представления
    }
    @GetMapping("/exchange")
    public String exchange(){
        return "api/Currency";
    }
    @GetMapping("/pay")
    public String pay(){
        return "api/pay";
    }


}
