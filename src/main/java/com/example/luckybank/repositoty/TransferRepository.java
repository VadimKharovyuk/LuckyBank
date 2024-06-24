package com.example.luckybank.repositoty;

import com.example.luckybank.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer,Long> {
    List<Transfer> findBySenderCard_CardNumberOrRecipientCard_CardNumber(String senderCardNumber, String recipientCardNumber);
}
