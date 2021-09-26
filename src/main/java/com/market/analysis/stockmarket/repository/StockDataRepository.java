package com.market.analysis.stockmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.market.analysis.stockmarket.entity.StockData;

@Repository
public interface StockDataRepository extends JpaRepository<StockData, Long>{

}
