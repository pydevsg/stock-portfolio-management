// src/main/java/com/stock/spm/exception/StockNotFoundException.java
package com.stock.spm.exception;

public class StockNotFoundException extends Exception {
    public StockNotFoundException(String message) {
        super(message);
    }
}