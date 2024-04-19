package com.example.luckybank.service;

import com.example.luckybank.model.Card;
import com.example.luckybank.repositoty.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.function.Supplier;


@Service
@AllArgsConstructor
public class CardService {

    private final CardRepository cardRepository;


    public Card createCard(Card card) {
        return cardRepository.save(card);
    }


    public void deleteCardById(Long id) {
        cardRepository.deleteById(id);
    }

    public Card orElseThrow(Supplier<? extends RuntimeException> exceptionSupplier) {
        throw exceptionSupplier.get();
    }


    public void saveCard(Card card) {
        cardRepository.save(card);
    }


    public List<Card> getAllCards() throws CardNotFoundException {
        List<Card> cards = cardRepository.findAll();
        if (cards.isEmpty()) {
            throw new CardNotFoundException("Список карт пуст");
        }
        return cards;
    }
    // Метод для получения карты по номеру

    public Card getCardByNumber(String cardNumber) throws CardNotFoundException {
        List<Card> cards = cardRepository.findByCardNumber(cardNumber);
        if (cards.isEmpty()) {
            throw new CardNotFoundException("Карта с номером " + cardNumber + " не найдена");
        }
        // Если список не пустой, возвращаем первую карту из списка
        return cards.get(0);
    }



    // Метод для проверки баланса и списания средств с карты отправителя
    public void debitBalance(String senderCardNumber, double amount) throws CardNotFoundException, InsufficientFundsException {
        Card senderCard = getCardByNumber(senderCardNumber);
        double currentBalance = senderCard.getBalance();
        if (currentBalance < amount) {
            throw new InsufficientFundsException("Недостаточно средств на карте " + senderCardNumber);
        }
        senderCard.setBalance(currentBalance - amount);
        cardRepository.save(senderCard);
    }


}




