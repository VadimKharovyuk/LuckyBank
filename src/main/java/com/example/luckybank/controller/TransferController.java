package com.example.luckybank.controller;

import com.example.luckybank.service.TransferService;
import com.example.luckybank.сonfiguration.TransferMessage;
import com.example.luckybank.сonfiguration.TransferSender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@AllArgsConstructor
public class TransferController {
    private final TransferService transferService;
    private final TransferSender transferSender;

    @GetMapping("/sendmoney")
    public String sendModey() {
        return "transfer";
    }


    @PostMapping("/transfer")
    public String transferFunds(@RequestParam("senderCardNumber") String senderCardNumber,
                                @RequestParam("recipientCardNumber") String recipientCardNumber,
                                @RequestParam("amount") double amount) throws Throwable {
        System.out.println("Перевод успешно отправлен в обработку ");
       transferSender.send(senderCardNumber, recipientCardNumber, amount);
        return "redirect:/confirmation";
    }


    @GetMapping("/confirmation")
    public String confirmation() {
        return "confirmation";
    }
}
