package com.example.luckybank.listener;
import com.example.luckybank.pojo.TransferMessage;
import com.example.luckybank.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferReceiver {



    @RabbitListener(queues = "transferQueue")
    public void receiveTransferMessage(TransferMessage transferMessage) {

            System.out.println("Первый консюмер обработал сообщение: " + transferMessage.getAmount());

    }

    @RabbitListener(queues = "transferQueue")
    public void receiveNewMessage(TransferMessage transferMessage) {

            System.out.println("Второй консюмер обработал сообщение: " + transferMessage.getAmount());

    }
}
