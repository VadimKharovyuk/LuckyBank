package com.example.luckybank.wow.mono_bank;

import java.util.HashMap;
import java.util.Map;

public class CurrencyNames {
    public static final Map<Integer, String> CURRENCY_NAMES = new HashMap<>();

    static {
        // Добавляем валюты в статический блок
        CURRENCY_NAMES.put(840, "USD - US Dollar");
        CURRENCY_NAMES.put(978, "EUR - Euro");
        CURRENCY_NAMES.put(980, "UAH - Ukrainian Hryvnia");
        // Добавляем другие валюты по мере необходимости
        CURRENCY_NAMES.put(978, "EUR - Euro");  // Например, добавлена еще одна валюта
        CURRENCY_NAMES.put(643, "RUB - Russian Ruble");  // Пример новой валюты
        CURRENCY_NAMES.put(826, "GBP - British Pound");  // Пример новой валюты
        // Добавьте остальные валюты здесь
    }

    // Метод для получения имени валюты по коду
    public static String getCurrencyName(int currencyCode) {
        return CURRENCY_NAMES.getOrDefault(currencyCode, "Unknown Currency");
    }
}
