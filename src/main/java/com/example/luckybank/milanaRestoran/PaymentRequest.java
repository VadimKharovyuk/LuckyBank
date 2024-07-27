package com.example.luckybank.milanaRestoran;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PaymentRequest {
    private String cardNumber;
    private double amount;
    private Date expirationDate;
    private String cvv;


}
