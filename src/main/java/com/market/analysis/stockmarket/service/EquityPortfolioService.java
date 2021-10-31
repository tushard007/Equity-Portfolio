package com.market.analysis.stockmarket.service;

import com.market.analysis.stockmarket.entity.EquityPortfolio;
import com.market.analysis.stockmarket.repository.EquityPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquityPortfolioService {

    @Autowired
    EquityPortfolioRepository equityPortfolioRepository;

    // Get all EquityPortfolio Data
    public List<EquityPortfolio> findAll() {
        return equityPortfolioRepository.findAll();
    }

    // Get EquityPortfolio data by Id
    public EquityPortfolio findByID(long id) {
        if (equityPortfolioRepository.findById(id).isPresent()) {
            return equityPortfolioRepository.findById(id).get();
        }
        return null;
    }

    // Create or update EquityPortfolio data
    public EquityPortfolio save(EquityPortfolio equityPortfolio) {
        equityPortfolioRepository.save(equityPortfolio);
        return equityPortfolio;
    }

    // Save all equity portfolio list
    public List<EquityPortfolio> SaveAllList(List<EquityPortfolio> equityPortfolioList) {
        equityPortfolioRepository.saveAll(equityPortfolioList);
        return equityPortfolioList;
    }

    // Delete EquityPortfolio data by id
    public void delete(Long id) {
        EquityPortfolio equityPortfolio = findByID(id);
        equityPortfolioRepository.delete(equityPortfolio);
    }
}