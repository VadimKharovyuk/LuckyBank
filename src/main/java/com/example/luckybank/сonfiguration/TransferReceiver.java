package com.example.luckybank.сonfiguration;

import com.example.luckybank.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferReceiver {

    private final TransferService transferService;

    @RabbitListener(queues = "transferQueue")
    public void receiveMessage(TransferMessage transferMessage) throws Throwable {
        transferService.transfer(
                transferMessage.getSenderCardNumber(),
                transferMessage.getRecipientCardNumber(),
                transferMessage.getAmount()
        );
    }
}
