package com.stock.spm.config;

import com.stock.spm.service.StockService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class StockControllerTestConfig {
    @Bean
    public StockService stockService() {
        return Mockito.mock(StockService.class);
    }
}