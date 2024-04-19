package com.example.luckybank.controller;

import com.example.luckybank.model.Card;
import com.example.luckybank.model.Client;
import com.example.luckybank.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;
    @GetMapping("/clients")
    public String clientsAll(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);

        for (Client client : clients) {
            List<Card> cards = clientService.getCardsByClientId(client.getId());
            model.addAttribute("cards_" + client.getId(), cards);
        }

        return "clients";

}
}
