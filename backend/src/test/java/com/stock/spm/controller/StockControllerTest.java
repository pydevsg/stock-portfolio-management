package com.stock.spm.controller;

import com.stock.spm.config.StockControllerTestConfig;
import com.stock.spm.model.Stock;
import com.stock.spm.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StockController.class)
@Import(StockControllerTestConfig.class)
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockService stockService;

    @Test
    void createStock_ShouldReturn200WithStock_WhenValid() throws Exception {
        Stock mockStock = Stock.builder()
                .id(1L)
                .symbol("AAPL")
                .name("Apple Inc.")
                .quantity(10)
                .purchasePrice(150.0)
                .build();

        when(stockService.addStock(any(Stock.class))).thenReturn(mockStock);

        mockMvc.perform(post("/api/stocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "symbol": "AAPL",
                        "name": "Apple Inc.",
                        "quantity": 10,
                        "purchasePrice": 150.0
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.symbol").value("AAPL"));
    }

//    @Test
//    void createStock_ShouldReturn400_WhenInvalid() throws Exception {
//        mockMvc.perform(post("/api/stocks")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                    {
//                        "symbol": "",
//                        "quantity": 0
//                    }
//                    """))
//                .andExpect(status().isBadRequest());
//    }

    @Test
    void getStockById_ShouldReturn200_WhenExists() throws Exception {
        Stock mockStock = Stock.builder()
                .id(1L)
                .symbol("MSFT")
                .build();

        when(stockService.getStockById(1L)).thenReturn(mockStock);

        mockMvc.perform(get("/api/stocks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.symbol").value("MSFT"));
    }
}