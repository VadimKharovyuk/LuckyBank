package com.example.luckybank.controller;
import com.example.luckybank.model.Client;
import com.example.luckybank.service.ClientService;
import com.example.luckybank.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/email")
@AllArgsConstructor
public class EmailController {

    private final ClientService clientService;
    private final EmailService emailService;

    @GetMapping("/sendToAllForm")
    public String showSendEmailForm(Model model) {
        return "sendEmailForm";
    }

    @PostMapping("/sendToAll")
    public String sendEmailToAllClients(@RequestParam String subject, @RequestParam String text, Model model) {
        List<Client> clients = clientService.getAllClientsWithEmail();
        emailService.sendMassEmail(subject, text, clients);
        model.addAttribute("message", "Ваше письмо отправленно  всем  клиентам");
        return "sendEmailResult";
    }
}
