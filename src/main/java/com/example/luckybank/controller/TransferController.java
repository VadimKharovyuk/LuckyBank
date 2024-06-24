package com.example.luckybank.controller;

import com.example.luckybank.Exception.InsufficientFundsException;
import com.example.luckybank.model.Transfer;
import com.example.luckybank.service.TransferService;
import com.example.luckybank.сonfiguration.TransferSender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class TransferController {
    private final TransferService transferService;
    private final TransferSender transferSender;

    @GetMapping("/form")
    public String showTransferForm() {
        return "transferForm";
    }

    @PostMapping("/history")
    public String getTransfersByCardNumber(@RequestParam String cardNumber, Model model) {
        List<Transfer> transfers = transferService.getTransfersByCardNumber(cardNumber);
        model.addAttribute("transfers", transfers);
        model.addAttribute("cardNumber", cardNumber);
        return "transferHistory";
    }



    @GetMapping("/sendmoney")
    public String sendModey() {
        return "transfer";
    }


    @PostMapping("/transfer")
    public String transferFunds(@RequestParam("senderCardNumber") String senderCardNumber,
                                @RequestParam("recipientCardNumber") String recipientCardNumber,
                                @RequestParam("amount") double amount) throws Throwable {
        // Вызываем метод transfer из TransferService
        double transferredAmount = transferService.transfer(senderCardNumber, recipientCardNumber, amount);

        // Если перевод успешен, отправляем сообщение в RabbitMQ или выполняем другие действия
        if (transferredAmount > 0) {
            transferSender.send(senderCardNumber, recipientCardNumber, transferredAmount);
        } else {
            throw new InsufficientFundsException("Недостаточно средств на счете отправителя");
        }

        return "redirect:/confirmation";
    }




    @GetMapping("/confirmation")
    public String confirmation() {
        return "confirmation";
    }
}
