package com.example.luckybank.controller;

import com.example.luckybank.сonfiguration.TransferMessage;
import com.example.luckybank.сonfiguration.TransferSender;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransferRestController {
    private final TransferSender transferSender;

    @PostMapping("/sendTransfer")
    public void sendTransfer(@RequestBody TransferMessage transferMessage) {
        transferSender.send(transferMessage.getSenderCardNumber(), transferMessage.getRecipientCardNumber(), transferMessage.getAmount());
    }

}
