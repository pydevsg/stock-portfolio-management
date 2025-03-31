// src/main/java/com/stock/spm/service/StockService.java
package com.stock.spm.service;

import com.stock.spm.exception.MarketDataException;
import com.stock.spm.exception.StockNotFoundException;
import com.stock.spm.model.Stock;
import java.util.List;

public interface StockService {
    // Existing methods
    Stock addStock(Stock stock);
    List<Stock> getAllStocks();
    void deleteStock(Long id);
    double getPortfolioValue();

    // Add these new methods
    Stock getStockById(Long id) throws StockNotFoundException;
    Stock getStockBySymbol(String symbol) throws StockNotFoundException;
    Stock updateStock(Long id, Stock stock) throws StockNotFoundException;
    double getCurrentPrice(String symbol) throws MarketDataException;
}