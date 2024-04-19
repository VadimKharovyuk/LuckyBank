package com.example.luckybank.controller;


import com.example.luckybank.service.BalanceCalculationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@AllArgsConstructor
public class BalanceController {

    private final BalanceCalculationService balanceCalculationService;

    @GetMapping("/total-balance")
    public String getTotalBalance(Model model) {
        double totalBalance = balanceCalculationService.calculateTotalBalance();
        model.addAttribute("totalBalance", totalBalance);
        return "total_balance"; // Имя представления
    }
}
