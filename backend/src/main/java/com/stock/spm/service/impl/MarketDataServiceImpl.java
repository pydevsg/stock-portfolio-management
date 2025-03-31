package com.stock.spm.service.impl;

import com.stock.spm.client.MarketDataClient;
import com.stock.spm.service.MarketDataService;
import com.stock.spm.exception.MarketDataException;
import org.springframework.stereotype.Service;

@Service
public class MarketDataServiceImpl implements MarketDataService {

    private final MarketDataClient marketDataClient;

    public MarketDataServiceImpl(MarketDataClient marketDataClient) {
        this.marketDataClient = marketDataClient;
    }

    @Override
    public double getCurrentPrice(String symbol) throws MarketDataException {
        return marketDataClient.getCurrentPrice(symbol);
    }
}