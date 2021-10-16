package com.market.analysis.stockmarket.repository;


import com.market.analysis.stockmarket.entity.EquityPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquityPortfolioRepository extends JpaRepository<EquityPortfolio, Long> {
}
