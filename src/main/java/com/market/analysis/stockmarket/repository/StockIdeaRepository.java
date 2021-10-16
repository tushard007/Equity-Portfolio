package com.market.analysis.stockmarket.repository;

import com.market.analysis.stockmarket.entity.StockIdea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockIdeaRepository extends JpaRepository<StockIdea,Long> {

}
