package com.example.luckybank.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransferMessage implements Serializable {
    private String senderCardNumber;
    private String recipientCardNumber;
    private double amount;
}