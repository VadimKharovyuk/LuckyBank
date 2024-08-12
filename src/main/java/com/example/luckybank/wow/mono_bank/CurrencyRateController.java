package com.example.luckybank.wow.mono_bank;

import com.example.luckybank.wow.mono_bank.CurrencyRate;
import com.example.luckybank.wow.mono_bank.CurrencyRateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/currency")
@AllArgsConstructor
public class CurrencyRateController {

    private final CurrencyRateService currencyRateService;


    @GetMapping
    public String getCurrencyRates(Model model) {
        CurrencyRate[] rates = currencyRateService.getCurrencyRates();
        model.addAttribute("rates", rates);
        return "apiMono/currencyRates";
    }


}
