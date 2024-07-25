package com.example.luckybank.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class CurrencyApiClient {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/95fe1a78ecd61c7fb38e32c3/latest/USD";

    private final OkHttpClient client = new OkHttpClient();

    public String getExchangeRates() throws IOException {
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }
}
