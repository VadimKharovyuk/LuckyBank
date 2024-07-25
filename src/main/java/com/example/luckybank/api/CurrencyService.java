//package com.example.luckybank.api;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.IOException;
//import java.util.Iterator;
//import java.util.Map;
//
//
//public class CurrencyService {
//    private final CurrencyApiClient apiClient = new CurrencyApiClient();
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    public String getExchangeRates() throws IOException {
//        String jsonResponse = apiClient.getExchangeRates();
//        JsonNode rootNode = objectMapper.readTree(jsonResponse);
//        JsonNode ratesNode = rootNode.path("conversion_rates");
//
//        StringBuilder result = new StringBuilder();
//        for (Iterator<Map.Entry<String, JsonNode>> it = ratesNode.fields(); it.hasNext(); ) {
//            Map.Entry<String, JsonNode> entry = it.next();
//            result.append("Currency: ").append(entry.getKey())
//                    .append(", Rate: ").append(entry.getValue().asDouble()).append("\n");
//        }
//
//        return result.toString();
//    }
//}
package com.example.luckybank.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class CurrencyService {
    private final CurrencyApiClient apiClient = new CurrencyApiClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode getExchangeRates() throws IOException {
        String jsonResponse = apiClient.getExchangeRates();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        return rootNode.path("conversion_rates");
    }
}
