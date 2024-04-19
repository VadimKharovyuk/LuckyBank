package com.example.luckybank.controller;

import com.example.luckybank.service.BalanceCalculationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BalanceController {

    private final BalanceCalculationService balanceCalculationService;

    @GetMapping("/total-balance")
    public ResponseEntity<Double> getTotalBalance() {
        double totalBalance = balanceCalculationService.calculateTotalBalance();
        return ResponseEntity.ok(totalBalance);
    }
}
