package com.example.luckybank.service;

import com.example.luckybank.Exception.InsufficientFundsException;
import com.example.luckybank.model.Card;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferService {


    private final CardService cardService;

    public void transfer(String senderCardNumber, String recipientCardNumber, double amount) throws Throwable {
        // Получаем информацию о картах отправителя и получателя
        Card senderCard = cardService.getCardByNumber(senderCardNumber);
        Card recipientCard = cardService.getCardByNumber(recipientCardNumber);

        // Проверяем, достаточно ли средств у отправителя
        if (senderCard.getBalance() >= amount) {
            // Вычитаем сумму перевода со счета отправителя
            senderCard.setBalance(senderCard.getBalance() - amount);
            // Добавляем сумму перевода на счет получателя
            recipientCard.setBalance(recipientCard.getBalance() + amount);

            // Сохраняем изменения в базе данных
            cardService.saveCard(senderCard);
            cardService.saveCard(recipientCard);
        } else {
            throw new InsufficientFundsException("Недостаточно средств на счете отправителя");
        }
    }
}
