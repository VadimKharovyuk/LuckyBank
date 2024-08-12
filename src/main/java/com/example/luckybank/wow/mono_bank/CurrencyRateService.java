package com.example.luckybank.wow.mono_bank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyRateService {

    @Value("${monobank.api.url}")
    private String apiUrl;

    @Value("${monobank.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public CurrencyRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CurrencyRate[] getCurrencyRates() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<CurrencyRate[]> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, CurrencyRate[].class);

        if (response.getBody() == null) {
            System.out.println("API response body is null");
        } else {
            System.out.println("API response received: " + Arrays.toString(response.getBody()));
        }

        return response.getBody();
    }



}
