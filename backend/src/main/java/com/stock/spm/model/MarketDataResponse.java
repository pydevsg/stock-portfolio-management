// src/main/java/com/stock/spm/model/MarketDataResponse.java
package com.stock.spm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MarketDataResponse {
    @JsonProperty("Global Quote")
    private GlobalQuote globalQuote;

    @Data
    public static class GlobalQuote {
        @JsonProperty("05. price")
        private double price;
    }

    public double getPrice() {
        return globalQuote != null ? globalQuote.getPrice() : 0.0;
    }
}