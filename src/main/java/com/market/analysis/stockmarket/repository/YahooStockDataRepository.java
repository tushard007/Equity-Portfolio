package com.market.analysis.stockmarket.repository;

import com.market.analysis.stockmarket.entity.YahooStockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface YahooStockDataRepository extends JpaRepository<YahooStockData, Long>{
    @Query(value = "SELECT m.stock_company_id FROM t_yahoo_stock_data m ORDER BY m.stock_company_id DESC LIMIT 1", nativeQuery = true)
    public Long getStockQuoteTableLastRow();

}
