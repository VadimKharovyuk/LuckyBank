package com.example.luckybank.milanaRestoran;

import com.example.luckybank.model.Card;
import com.example.luckybank.model.Client;
import com.example.luckybank.model.Transfer;
import com.example.luckybank.service.CardService;
import com.example.luckybank.service.EmailService;
import com.example.luckybank.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;


@RestController
@RequestMapping("/api/payments")

public class PaymentController {

    @Autowired
    private CardService cardService;

    @Autowired
    private TransferService transferService;
    @Autowired
    private EmailService emailService;


@PostMapping
public String processPayment(@RequestBody PaymentRequest paymentRequest) {
    String cardNumber = paymentRequest.getCardNumber();
    double amount = paymentRequest.getAmount();
    Date expirationDate = paymentRequest.getExpirationDate();
    String cvv = paymentRequest.getCvv();


    // Проверка срока действия карты
    if (expirationDate == null) {
        return "Expiration date is required";
    }

    // Проверка формата CVV
    if (cvv == null || cvv.length() != 3) {
        return "Invalid CVV";
    }

    // Проверка срока действия карты
    Date currentDate = new Date(); // Можно использовать java.util.Date или java.time.LocalDate
    if (expirationDate.before(currentDate)) {
        return "Card has expired";
    }

    // Поиск карты по номеру
    Card card = cardService.getCardByNumber(cardNumber);
    if (card == null) {
        return "Card not found";
    }

    // Проверка баланса
    if (card.getBalance() < amount) {
        return "Insufficient funds";
    }

    // Обновление баланса карты
    card.setBalance(card.getBalance() - amount);
    cardService.saveCard(card);

    // Добавление записи о переводе
    Transfer transfer = new Transfer();
    transfer.setSenderCard(card);
    transfer.setAmount(amount);
    transfer.setTimestamp(new Timestamp(System.currentTimeMillis()));
    transferService.saveTransfer(transfer);
    // Отправка уведомления о платеже
    String clientEmail = card.getClient().getEmail();
    Client client = card.getClient(); // Получаем клиента из карты
    emailService.sendPaymentNotification(clientEmail, amount,client);


    return "Payment successful";
}



}
