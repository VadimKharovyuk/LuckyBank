package com.example.luckybank.controller;//package com.example.luckybank.controller;
//
//import com.example.luckybank.model.Card;
//import com.example.luckybank.model.Client;
//import com.example.luckybank.service.CardService;
//import com.example.luckybank.service.ClientService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Date;
//import java.util.List;
//
//@Controller
//@RequestMapping("/cards")
//@AllArgsConstructor
//public class CardController {
//
//    private final CardService cardService;
//    private final ClientService clientService;
//
//    @GetMapping("/create")
//    public String showCreateCardForm(Model model) {
//        // Показать форму для создания карточки
//        List<Client> clients = clientService.getAllClients(); // Получаем всех клиентов из базы данных
//        model.addAttribute("clients", clients); // Добавляем список клиентов в модель
//        return "create_card";
//    }
//
//    @PostMapping("/createcard")
//    public String createCard(@RequestParam String cardNumber,
//                             @RequestParam Date expirationDate,
//                             @RequestParam String cvv,
//                             @RequestParam double balance,
//                             @RequestParam String clientName) {
//        // Создать новую карточку и связать ее с клиентом
//        Card card = new Card();
//        card.setCardNumber(cardNumber);
//        card.setExpirationDate((java.sql.Date) expirationDate);
//        card.setCvv(cvv);
//        card.setBalance(balance);
//
//        // Получить клиента по имени и связать с карточкой
//        Client client = clientService.getClientByName(clientName);
//        card.setClient(client);
//
//        // Сохранить карточку
//        cardService.createCard(card);
//
//        return "redirect:/";
//    }
//
//}

import com.example.luckybank.model.Card;
import com.example.luckybank.model.Client;
import com.example.luckybank.service.CardService;
import com.example.luckybank.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class CardController {


    private final CardService cardService;


    private final ClientService clientService;

    @GetMapping("/cards/create")
    public String showCreateCardForm(Model model) {
        // Получаем список всех клиентов из базы данных
        List<Client> clients = clientService.getAllClients();
        // Добавляем список клиентов в модель
        model.addAttribute("clients", clients);
        // Возвращаем имя представления
        return "create_card";
    }

    @PostMapping("/cards/createcard")
    public String createCard(@RequestParam String cardNumber,
                             @RequestParam Date expirationDate,
                             @RequestParam String cvv,
                             @RequestParam double balance,
                             @RequestParam String clientName) {
        // Получаем клиента по его имени из базы данных
        Client client = clientService.getClientByName(clientName);
        // Создаем новую карту
        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setExpirationDate(expirationDate);
        card.setCvv(cvv);
        card.setBalance(balance);
        card.setClient(client); // Связываем карту с клиентом
        // Сохраняем карту в базе данных
        cardService.createCard(card);
        // Перенаправляем пользователя на главную страницу или куда-либо еще
        return "redirect:/";
    }
    @GetMapping("/cards")
    public String getAllCards(Model model) {
        // Получаем список всех карточек из базы данных
        List<Card> cards = cardService.getAllCards();
        // Добавляем список карточек в модель
        model.addAttribute("cards", cards);
        // Возвращаем имя представления
        return "cards";
    }
}

