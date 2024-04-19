package com.example.luckybank.service;

import com.example.luckybank.model.Card;
import com.example.luckybank.repositoty.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CardService {

    private  final CardRepository cardRepository;

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public List<Card> getAllCards() {
      return   cardRepository.findAll();
    }


    public void deleteCardById(Long id) {
        cardRepository.deleteById(id);
    }
}
