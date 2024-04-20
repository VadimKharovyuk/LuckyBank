package com.example.luckybank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String address;
    private String email;
    private String balance;


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Card> cards;

    @ManyToMany
    @JoinTable(name = "client_food",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> foods;
}



