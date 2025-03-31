package com.stock.spm.util;

import com.stock.spm.model.Stock;

public class TestStockBuilder {
    public static Stock.StockBuilder defaultStock() {
        return Stock.builder()
                .id(1L)
                .symbol("AAPL")
                .name("Apple Inc.")
                .quantity(10)
                .purchasePrice(150.0);
    }
}
