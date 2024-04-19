package com.example.luckybank.model;

import com.example.luckybank.Exception.CardNotFoundException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private Date expirationDate;
    private String cvv;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Card orElseThrow(Object o) {
        throw new CardNotFoundException("Карта не найдена");
    }

}
