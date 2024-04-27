package com.example.luckybank.controller;//package com.example.luckybank.controller;

import com.example.luckybank.model.Card;
import com.example.luckybank.model.Client;
import com.example.luckybank.Exception.CardNotFoundException;
import com.example.luckybank.service.CardService;
import com.example.luckybank.service.ClientService;
import com.example.luckybank.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

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
    public String createCard(
            @RequestParam String cardNumber,
            @RequestParam Date expirationDate,
            @RequestParam String cvv,
            @RequestParam double balance,
            @RequestParam String clientName,
            RedirectAttributes redirectAttributes) {
        // Получаем клиента по его имени из базы данных
        Optional<Client> optionalClient = clientService.getClientByName(clientName);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();

            // Создаем новую карту
            Card card = new Card();
            card.setCardNumber(cardNumber);
            card.setExpirationDate(expirationDate);
            card.setCvv(cvv);
            card.setBalance(balance);
            card.setClient(client); // Связываем карту с клиентом

            // Сохраняем карту в базе данных
            cardService.createCard(card);

            // Перенаправляем пользователя на страницу успешного создания карты или на главную страницу
            redirectAttributes.addFlashAttribute("successMessage", "Карта успешно создана!");
            return "redirect:/cards"; // Направляем пользователя на страницу списка карт или на другую релевантную страницу
        } else {
            // Если клиент не найден, обрабатываем ошибку
            redirectAttributes.addFlashAttribute("errorMessage", "Клиент не найден!");
            return "redirect:/cards/create"; // Возвращаем пользователя на страницу создания карты или на другую релевантную страницу
        }
    }




    @GetMapping("/cards")
    public String getAllCards(Model model) {
        try {
            // Получаем список всех карточек из базы данных
            List<Card> cards = cardService.getAllCards();
            // Добавляем список карточек в модель
            model.addAttribute("cards", cards);
            // Возвращаем имя представления
            return "cards";
        } catch (CardNotFoundException e) {
            // Обработка исключения, если список карт пуст
            // В данном случае можно вернуть страницу с сообщением об ошибке или перенаправить на другую страницу
            return "errorPage"; // Замените "errorPage" на имя вашего шаблона страницы с ошибкой
        }
    }


    @PostMapping("/cards/delete/{id}")
    public String deleteCard(@PathVariable Long id) {
        // Удаляем карту по её ID
        cardService.deleteCardById(id);
        // Перенаправляем пользователя на страницу со списком карточек или куда-либо еще
        return "redirect:/cards";
    }
}

