// src/main/java/com/stock/spm/service/MarketDataService.java
package com.stock.spm.service;

import com.stock.spm.exception.MarketDataException;

public interface MarketDataService {
    double getCurrentPrice(String symbol) throws MarketDataException;
}
