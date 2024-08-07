package com.example.luckybank.service;
import com.example.luckybank.Exception.InsufficientFundsException;
import com.example.luckybank.pojo.TransferMessage;
import com.example.luckybank.repositoty.TransferRepository;
import com.example.luckybank.model.Card;
import com.example.luckybank.model.Transfer;
import com.example.luckybank.сonfiguration.RabbitMQConfig;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class TransferService {
    private final RabbitTemplate rabbitTemplate;

    private final CardService cardService;
    private final TransferRepository transferRepository;
    private final CacheManager cacheManager;


    public double transfer(String senderCardNumber, String recipientCardNumber, double amount) throws Throwable {
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

            // Создаем запись о транзакции
            Transfer transfer = new Transfer();
            transfer.setSenderCard(senderCard);
            transfer.setRecipientCard(recipientCard);
            transfer.setAmount(amount);
            transfer.setTimestamp(new Timestamp(System.currentTimeMillis()));

            // Сохраняем запись о транзакции
            transferRepository.save(transfer);

            return amount; // Возвращаем сумму перевода для информации или проверок
        } else {
            throw new InsufficientFundsException("Недостаточно средств на счете отправителя");
        }

    }
    public void sendTransactionMessage(String senderCardNumber, String recipientCardNumber, double amount) {
        TransferMessage transferMessage = new TransferMessage(senderCardNumber, recipientCardNumber, amount);
        rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeName, "", transferMessage);
    }


    @Cacheable(value = "transfers", key = "#cardNumber")
    public List<Transfer> getTransfersByCardNumber(String cardNumber) {
        try {
            // Проверка соединения с Redis
            cacheManager.getCache("transfers").get(cardNumber);
            // Если соединение есть, возвращаем кэшированные данные
            return transferRepository.findBySenderCard_CardNumberOrRecipientCard_CardNumber(cardNumber, cardNumber);
        } catch (RedisConnectionFailureException ex) {
            // Если соединения с Redis нет, возвращаем данные из базы данных
            System.err.println("Redis connection failed, пошел в бд.");
            return transferRepository.findBySenderCard_CardNumberOrRecipientCard_CardNumber(cardNumber, cardNumber);
        }
    }

    public void saveTransfer(Transfer transfer) {
        transferRepository.save(transfer);
    }
}
