package com.example.luckybank.milanaRestoran;

import com.example.luckybank.model.Card;
import com.example.luckybank.model.Transfer;
import com.example.luckybank.service.CardService;
import com.example.luckybank.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private CardService cardService;

    @Autowired
    private TransferService transferService;


    @PostMapping
    public String processPayment(@RequestBody PaymentRequest paymentRequest) {
        String cardNumber = paymentRequest.getCardNumber();
        double amount = paymentRequest.getAmount();

        Card card = cardService.getCardByNumber(cardNumber);
        if (card == null) {
            return "Card not found";
        }
        if (card.getBalance() < amount) {
            return "Insufficient funds";
        }

        card.setBalance(card.getBalance() - amount);
        cardService.saveCard(card);

        // Добавьте запись о переводе, если необходимо
        Transfer transfer = new Transfer();
        transfer.setSenderCard(card);
        transfer.setAmount(amount);
        transfer.setTimestamp(new Timestamp(System.currentTimeMillis()));
        transferService.saveTransfer(transfer);

        return "Payment successful";
    }

}
