package com.example.luckybank.repositoty;

import com.example.luckybank.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {

    List<Card> findByCardNumber(String cardNumber);


    List<Card> findByClientId(Long id);

}
