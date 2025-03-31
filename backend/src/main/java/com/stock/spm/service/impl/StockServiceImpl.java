package com.stock.spm.service.impl;

import com.stock.spm.exception.MarketDataException;
import com.stock.spm.exception.StockNotFoundException;
import com.stock.spm.model.Stock;
import com.stock.spm.repository.StockRepository;
import com.stock.spm.service.MarketDataService;
import com.stock.spm.service.StockService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final MarketDataService marketDataService;

    public StockServiceImpl(StockRepository stockRepository,
                            MarketDataService marketDataService) {
        this.stockRepository = stockRepository;
        this.marketDataService = marketDataService;
    }

    @Override
    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getStockById(Long id) throws StockNotFoundException {
        return stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException("Stock not found with id: " + id));
    }

    @Override
    public Stock getStockBySymbol(String symbol) throws StockNotFoundException {
        return stockRepository.findBySymbol(symbol)
                .orElseThrow(() -> new StockNotFoundException("Stock not found with symbol: " + symbol));
    }

    @Override
    public Stock updateStock(Long id, Stock stockDetails) throws StockNotFoundException {
        Stock stock = getStockById(id);
        stock.setSymbol(stockDetails.getSymbol());
        stock.setName(stockDetails.getName());
        stock.setQuantity(stockDetails.getQuantity());
        stock.setPurchasePrice(stockDetails.getPurchasePrice());
        return stockRepository.save(stock);
    }

    @Override
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    @Override
    public double getCurrentPrice(String symbol) throws MarketDataException {
        return marketDataService.getCurrentPrice(symbol);
    }

    @Override
    public double getPortfolioValue() {
        List<Stock> stocks = stockRepository.findAll();

        if (stocks.isEmpty()) {
            return 0.0;
        }

        return stocks.stream()
                .mapToDouble(stock -> {
                    try {
                        double currentPrice = marketDataService.getCurrentPrice(stock.getSymbol());
                        return currentPrice * stock.getQuantity();
                    } catch (MarketDataException e) {
                        return stock.getPurchasePrice() * stock.getQuantity();
                    }
                })
                .sum();
    }
}