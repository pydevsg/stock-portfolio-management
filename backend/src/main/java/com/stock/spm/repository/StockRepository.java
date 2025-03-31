// src/main/java/com/stock/spm/repository/StockRepository.java
package com.stock.spm.repository;

import com.stock.spm.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findBySymbol(String symbol);
}