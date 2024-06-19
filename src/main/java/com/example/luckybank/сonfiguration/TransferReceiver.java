package com.example.luckybank.сonfiguration;

import com.example.luckybank.service.TransferService;
import com.example.luckybank.сonfiguration.TransferMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferReceiver {

    private final TransferService transferService;

    @RabbitListener(queues = "transferQueue")
    public void receiveTransferMessage(TransferMessage transferMessage) throws Throwable {
        transferService.transfer(
                transferMessage.getSenderCardNumber(),
                transferMessage.getRecipientCardNumber(),
                transferMessage.getAmount()
        );
        System.out.println("transferQueue трансфер отправил " +transferMessage.getAmount() );
    }

    @RabbitListener(queues = "newTransferQueue")
    public void receiveNewMessage(TransferMessage transferMessage) {
        try {
            transferService.transfer(
                    transferMessage.getSenderCardNumber(),
                    transferMessage.getRecipientCardNumber(),
                    transferMessage.getAmount()
            );
            System.out.println("newTransferQueue трансфер отправил " + transferMessage.getAmount());
        } catch (Throwable e) {
            // Обработка ошибок, например, логирование исключения
            System.err.println("Ошибка при обработке сообщения из новой очереди: " + e.getMessage());
        }
    }

}
