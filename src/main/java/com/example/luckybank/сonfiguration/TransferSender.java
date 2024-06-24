package com.example.luckybank.сonfiguration;
import com.example.luckybank.pojo.TransferMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferSender {

    private final RabbitTemplate rabbitTemplate;

    public void send(String senderCardNumber, String recipientCardNumber, double amount) {
        TransferMessage transferMessage = new TransferMessage(senderCardNumber, recipientCardNumber, amount);
        rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeName, "", transferMessage);
    }
}
