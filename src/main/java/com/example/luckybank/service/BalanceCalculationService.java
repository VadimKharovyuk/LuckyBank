package com.example.luckybank.service;

import com.example.luckybank.model.Card;
import com.example.luckybank.model.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BalanceCalculationService {

    private final EntityManager entityManager;


    public double calculateTotalBalance() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> query = cb.createQuery(Double.class);
        Root<Card> root = query.from(Card.class); // Изменено на Card
        query.select(cb.sum(root.get("balance"))); // Изменено на "balance"
        Double totalBalance = entityManager.createQuery(query).getSingleResult();
        return totalBalance != null ? totalBalance : 0.0;
    }
}
