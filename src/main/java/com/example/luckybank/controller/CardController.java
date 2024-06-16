package com.example.luckybank.controller;

import com.example.luckybank.Exception.CardNotFoundException;
import com.example.luckybank.Exception.InsufficientFundsException;
import com.example.luckybank.model.Card;
import com.example.luckybank.model.Client;
import com.example.luckybank.service.CardService;
import com.example.luckybank.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class CardController {

    private final CardService cardService;
    private final ClientService clientService;

    @PostMapping("/cards/createcard")
    public String createCard(
            @RequestParam String clientName,
            RedirectAttributes redirectAttributes) {
        try {
            Card card = cardService.generateCard(clientName);
            redirectAttributes.addFlashAttribute("successMessage", "Карта успешно создана с номером: " + card.getCardNumber());
            return "redirect:/cards";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/cards/create";
        }
    }

    @GetMapping("/cards/create")
    public String showCreateCardForm(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "createCard";
    }

    @GetMapping("/cards")
    public String getAllCards(Model model) {
        try {
            List<Card> cards = cardService.getAllCards();
            model.addAttribute("cards", cards);
            return "cards";
        } catch (CardNotFoundException e) {
            return "errorPage"; // Обработка ошибки, если карточки не найдены
        }
    }

    @PostMapping("/cards/delete/{id}")
    public String deleteCard(@PathVariable Long id) {
        cardService.deleteCardById(id);
        return "redirect:/cards";
    }

    @PostMapping("/cards/credit")
    public String creditCardBalance(
            @RequestParam String cardNumber,
            @RequestParam double amount,
            RedirectAttributes redirectAttributes) {
        try {
            cardService.creditBalance(cardNumber, amount);
            redirectAttributes.addFlashAttribute("successMessage", "Баланс карты успешно пополнен!");
            return "redirect:/bankomat";
        } catch (CardNotFoundException | InsufficientFundsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/bankomat";
        }
    }

    @GetMapping("/bankomat")
    public String addMoney(Model model) {
        try {
            List<Card> cards = cardService.getAllCards();
            model.addAttribute("cards", cards);
            return "addBalance";
        } catch (CardNotFoundException e) {
            return "errorPage"; // Обработка ошибки, если карточки не найдены
        }
    }

}
