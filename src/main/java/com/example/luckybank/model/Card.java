package com.example.luckybank.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private Date expirationDate;
    private String cvv;
    private double balance;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;
    @JsonIgnore
    @OneToMany(mappedBy = "senderCard", cascade = CascadeType.REMOVE)
    private List<Transfer> outgoingTransfers;



}
