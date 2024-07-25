//package com.example.luckybank.api;
//
//import com.example.luckybank.api.CurrencyService;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/currency")
//public class CurrencyController {
//
//    private final CurrencyService currencyService = new CurrencyService();
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @GetMapping("/rates")
//    public Map<String, Object> getExchangeRates(@RequestParam(required = false) String currencies) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//            // Получаем данные в виде строки
//            String responseText = currencyService.getExchangeRates();
//
//            // Преобразуем строку в JSON формат
//            JsonNode rootNode = objectMapper.readTree(responseText);
//            JsonNode ratesNode = rootNode.path("conversion_rates");
//
//            if (currencies != null && !currencies.isEmpty()) {
//                Set<String> currencySet = Set.of(currencies.split(","));
//                Map<String, Double> filteredRates = currencySet.stream()
//                        .collect(Collectors.toMap(
//                                currency -> currency,
//                                currency -> ratesNode.path(currency).asDouble()
//                        ));
//
//                result.put("rates", filteredRates);
//            } else {
//                Map<String, Double> allRates = new HashMap<>();
//                ratesNode.fieldNames().forEachRemaining(currency ->
//                        allRates.put(currency, ratesNode.path(currency).asDouble())
//                );
//                result.put("rates", allRates);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            result.put("error", "Error retrieving exchange rates");
//        }
//
//        return result;
//    }
//}
package com.example.luckybank.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    private final CurrencyService currencyService = new CurrencyService();

    @GetMapping("/rates")
    public Map<String, Object> getExchangeRates(@RequestParam(required = false) String currencies) {
        Map<String, Object> result = new HashMap<>();
        try {
            JsonNode ratesNode = currencyService.getExchangeRates();

            if (currencies != null && !currencies.isEmpty()) {
                Set<String> currencySet = Set.of(currencies.split(","));
                Map<String, Double> filteredRates = currencySet.stream()
                        .collect(Collectors.toMap(
                                currency -> currency,
                                currency -> ratesNode.path(currency).asDouble()
                        ));

                result.put("rates", filteredRates);
            } else {
                Map<String, Double> allRates = new HashMap<>();
                ratesNode.fieldNames().forEachRemaining(currency ->
                        allRates.put(currency, ratesNode.path(currency).asDouble())
                );
                result.put("rates", allRates);
            }

        } catch (IOException e) {
            e.printStackTrace();
            result.put("error", "Error retrieving exchange rates");
        }

        return result;
    }
}
