package com.example.luckybank.listener;
import com.example.luckybank.pojo.TransferMessage;
import com.example.luckybank.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferReceiver {

    private final TransferService transferService;

    @RabbitListener(queues = "transferQueue")
    public void receiveTransferMessage(TransferMessage transferMessage) {
//        try {
//            transferService.transfer(
//                    transferMessage.getSenderCardNumber(),
//                    transferMessage.getRecipientCardNumber(),
//                    transferMessage.getAmount()
//            );
            System.out.println("Первый консюмер обработал сообщение: " + transferMessage.getAmount());
//        } catch (Exception e) {
//            System.err.println("Ошибка при обработке сообщения из очереди transferQueue: " + e.getMessage());
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
    }

    @RabbitListener(queues = "transferQueue")
    public void receiveNewMessage(TransferMessage transferMessage) {
//        try {
//            transferService.transfer(
//                    transferMessage.getSenderCardNumber(),
//                    transferMessage.getRecipientCardNumber(),
//                    transferMessage.getAmount()
//            );
            System.out.println("Второй консюмер обработал сообщение: " + transferMessage.getAmount());
//        } catch (Exception e) {
//            System.err.println("Ошибка при обработке сообщения из очереди transferQueue: " + e.getMessage());
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
    }
}
