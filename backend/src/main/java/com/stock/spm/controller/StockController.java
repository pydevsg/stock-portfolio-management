package com.stock.spm.controller;

import com.stock.spm.exception.MarketDataException;
import com.stock.spm.exception.StockNotFoundException;
import com.stock.spm.model.Stock;

import com.stock.spm.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllStocks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) throws StockNotFoundException {
        return ResponseEntity.ok(stockService.getStockById(id));
    }

    @GetMapping("/symbol/{symbol}")
    public ResponseEntity<Stock> getStockBySymbol(@PathVariable String symbol) throws StockNotFoundException {
        return ResponseEntity.ok(stockService.getStockBySymbol(symbol));
    }

    @PostMapping
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock) {
        return ResponseEntity.ok(stockService.addStock(stock));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(
            @PathVariable Long id,
            @RequestBody Stock stock) throws StockNotFoundException {
        return ResponseEntity.ok(stockService.updateStock(id, stock));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/portfolio/value")
    public ResponseEntity<Double> getPortfolioValue() {
        return ResponseEntity.ok(stockService.getPortfolioValue());
    }

    @GetMapping("/{symbol}/current-price")
    public ResponseEntity<Double> getCurrentPrice(@PathVariable String symbol) throws MarketDataException {
        return ResponseEntity.ok(stockService.getCurrentPrice(symbol));
    }

}
