package com.stock.spm.client;

import com.stock.spm.exception.MarketDataException;
import com.stock.spm.model.MarketDataResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MarketDataClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MarketDataClient marketDataClient;

    @Test
    void getCurrentPrice_ShouldParseResponse() throws Exception {
        MarketDataResponse mockResponse = new MarketDataResponse();
        MarketDataResponse.GlobalQuote quote = new MarketDataResponse.GlobalQuote();
        quote.setPrice(175.50);
        mockResponse.setGlobalQuote(quote);

        when(restTemplate.getForObject(anyString(), eq(MarketDataResponse.class)))
                .thenReturn(mockResponse);

        double price = marketDataClient.getCurrentPrice("AAPL");
        assertEquals(175.50, price);
    }

    @Test
    void getCurrentPrice_ShouldThrow_WhenEmptyResponse() throws Exception {
        when(restTemplate.getForObject(anyString(), eq(MarketDataResponse.class)))
                .thenReturn(null);
        assertThrows(MarketDataException.class, () ->
                marketDataClient.getCurrentPrice("AAPL"));
    }
}