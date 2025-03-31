package com.stock.spm.service;

import com.stock.spm.exception.MarketDataException;
import com.stock.spm.model.Stock;
import com.stock.spm.repository.StockRepository;
import com.stock.spm.service.impl.StockServiceImpl;
import com.stock.spm.util.TestStockBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private MarketDataService marketDataService;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    void getStockById_ShouldReturnStock_WhenExists() throws Exception {
        Stock mockStock = TestStockBuilder.defaultStock().build();
        when(stockRepository.findById(1L)).thenReturn(Optional.of(mockStock));

        Stock result = stockService.getStockById(1L);
        assertEquals("AAPL", result.getSymbol());
    }

    @Test
    void getPortfolioValue_ShouldFallback_WhenAPIFails() throws Exception {
        Stock stock = TestStockBuilder.defaultStock().build();
        when(stockRepository.findAll()).thenReturn(List.of(stock));
        when(marketDataService.getCurrentPrice("AAPL"))
                .thenThrow(new MarketDataException("API Down"));

        double value = stockService.getPortfolioValue();
        assertEquals(1500.0, value); // 10 shares × 150 purchase price
    }
}