package com.stock.spm.util;
import com.stock.spm.model.MarketDataResponse;
import com.stock.spm.model.Stock;

public class TestDataHelper {
    public static Stock createSampleStock() {
        Stock stock = new Stock();
        stock.setId(1L);
        stock.setSymbol("AAPL");
        stock.setName("Apple Inc.");
        stock.setQuantity(10);
        stock.setPurchasePrice(150.0);
        return stock;
    }

    public static MarketDataResponse createMarketDataResponse(double price) {
        MarketDataResponse response = new MarketDataResponse();
        MarketDataResponse.GlobalQuote quote = new MarketDataResponse.GlobalQuote();
        quote.setPrice(price);
        response.setGlobalQuote(quote);
        return response;
    }
}