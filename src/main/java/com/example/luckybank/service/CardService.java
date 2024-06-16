package com.example.luckybank.service;
import com.example.luckybank.Exception.CardNotFoundException;
import com.example.luckybank.Exception.InsufficientFundsException;
import com.example.luckybank.model.Card;
import com.example.luckybank.model.Client;
import com.example.luckybank.repositoty.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


@Service
@AllArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final ClientService clientService;
    private static final SecureRandom random = new SecureRandom();


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

    public void debitBalance(String senderCardNumber, double amount) throws CardNotFoundException, InsufficientFundsException {
        Card senderCard = getCardByNumber(senderCardNumber);
        double currentBalance = senderCard.getBalance();
        if (currentBalance < amount) {
            throw new InsufficientFundsException("Недостаточно средств на карте " + senderCardNumber);
        }
        senderCard.setBalance(currentBalance - amount);
        cardRepository.save(senderCard);
    }

    public List<Card> getCardsByClientId(Long id) {
        return cardRepository.findByClientId(id);
    }
    public Card generateCard(String clientName) {
        Optional<Client> optionalClient = clientService.getClientByName(clientName);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();

            String cardNumber = generateCardNumber();
            String cvv = generateCVV();
            Date expirationDate = generateExpirationDate();

            Card card = new Card();
            card.setCardNumber(cardNumber);
            card.setExpirationDate(expirationDate);
            card.setCvv(cvv);
            card.setBalance(0); // Начальный баланс
            card.setClient(client);

            return cardRepository.save(card);
        } else {
            throw new IllegalArgumentException("Клиент с именем " + clientName + "не найден!");
        }
    }

    private String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }

    private String generateCVV() {
        StringBuilder cvv = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            cvv.append(random.nextInt(10));
        }
        return cvv.toString();
    }

    private Date generateExpirationDate() {
        // Устанавливаем срок действия карты на 3 года с текущего момента
        LocalDate expirationLocalDate = LocalDate.now().plusYears(3);
        return Date.valueOf(expirationLocalDate);
    }

}




