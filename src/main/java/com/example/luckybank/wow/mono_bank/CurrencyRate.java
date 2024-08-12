package com.example.luckybank.wow.mono_bank;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRate {

    @JsonProperty("currencyCodeA")
    private int currencyCodeA;

    @JsonProperty("currencyCodeB")
    private int currencyCodeB;

    @JsonProperty("date")
    private long date;

    @JsonProperty("rateSell")
    private float rateSell;

    @JsonProperty("rateBuy")
    private float rateBuy;

    @JsonProperty("rateCross")
    private float rateCross;

    // Метод для получения имени валюты A
    public String getCurrencyNameA() {
        return CurrencyNames.getCurrencyName(currencyCodeA);
    }

    // Метод для получения имени валюты B
    public String getCurrencyNameB() {
        return CurrencyNames.getCurrencyName(currencyCodeB);
    }
}
