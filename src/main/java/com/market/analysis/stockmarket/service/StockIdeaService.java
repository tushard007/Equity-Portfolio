package com.market.analysis.stockmarket.service;

import com.market.analysis.stockmarket.entity.StockIdea;
import com.market.analysis.stockmarket.repository.StockIdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockIdeaService {

    @Autowired
    StockIdeaRepository stockResearchRepository;

    public List<StockIdea> saveStockResearchData(List<StockIdea> stockResearch) {
        stockResearchRepository.saveAll(stockResearch);
        return stockResearch;
    }
}
