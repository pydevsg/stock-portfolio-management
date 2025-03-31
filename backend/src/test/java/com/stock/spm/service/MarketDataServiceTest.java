package com.stock.spm.service;

import com.stock.spm.client.MarketDataClient;
import com.stock.spm.exception.MarketDataException;
import com.stock.spm.service.impl.MarketDataServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MarketDataServiceImplTest {

    @Mock
    private MarketDataClient marketDataClient;

    @InjectMocks
    private MarketDataServiceImpl marketDataService;

    @Test
    void getCurrentPrice_ShouldReturnPrice_WhenValidSymbol() throws Exception {
        when(marketDataClient.getCurrentPrice("AAPL")).thenReturn(175.50);
        double price = marketDataService.getCurrentPrice("AAPL");
        assertEquals(175.50, price);
    }

    @Test
    void getCurrentPrice_ShouldThrow_WhenInvalidSymbol() throws Exception {
        when(marketDataClient.getCurrentPrice("INVALID"))
                .thenThrow(new MarketDataException("Invalid symbol"));
        assertThrows(MarketDataException.class, () ->
                marketDataService.getCurrentPrice("INVALID"));
    }

    @Test
    void getCurrentPrice_ShouldThrow_WhenAPITimeout() throws Exception {
        when(marketDataClient.getCurrentPrice("AAPL"))
                .thenThrow(new MarketDataException("Timeout"));
        assertThrows(MarketDataException.class, () ->
                marketDataService.getCurrentPrice("AAPL"));
    }
}