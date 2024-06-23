package com.example.luckybank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_card_id")
    private Card senderCard;

    @ManyToOne
    @JoinColumn(name = "recipient_card_id")
    private Card recipientCard;

    private double amount;

    private Timestamp timestamp;
}
