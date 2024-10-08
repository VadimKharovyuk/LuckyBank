package com.example.luckybank.api;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class CurrencyService {
    private final CurrencyApiClient apiClient = new CurrencyApiClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode getExchangeRates() throws IOException {
        String jsonResponse = apiClient.getExchangeRates();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        return rootNode.path("conversion_rates");
    }
}
