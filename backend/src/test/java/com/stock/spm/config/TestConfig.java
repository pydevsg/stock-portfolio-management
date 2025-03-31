package com.stock.spm.config;

import com.stock.spm.service.MarketDataService;
import com.stock.spm.service.StockService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestConfig {
    @Bean
    @Primary
    public StockService stockService() {
        return Mockito.mock(StockService.class);
    }

    @Bean
    @Primary
    public MarketDataService marketDataService() {
        return Mockito.mock(MarketDataService.class);
    }
}