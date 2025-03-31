// src/main/java/com/stock/spm/client/MarketDataClient.java
package com.stock.spm.client;

import com.stock.spm.exception.MarketDataException;
import com.stock.spm.model.MarketDataResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MarketDataClient {
    private final RestTemplate restTemplate;

    @Value("${market-data.api.key}")
    private String apiKey;

    @Value("${market-data.api.url}")
    private String apiUrl;

    public MarketDataClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double getCurrentPrice(String symbol) throws MarketDataException {
        String url = String.format("%s?function=GLOBAL_QUOTE&symbol=%s&apikey=%s",
                apiUrl, symbol, apiKey);

        try {
            MarketDataResponse response = restTemplate.getForObject(url, MarketDataResponse.class);
            return response.getPrice();
        } catch (Exception e) {
            throw new MarketDataException("Failed to fetch price for " + symbol);
        }
    }
}