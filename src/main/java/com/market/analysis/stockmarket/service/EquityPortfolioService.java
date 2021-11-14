package com.market.analysis.stockmarket.service;

import com.market.analysis.stockmarket.entity.Company;
import com.market.analysis.stockmarket.entity.EquityPortfolio;
import com.market.analysis.stockmarket.repository.EquityPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class EquityPortfolioService {

    @Autowired
    EquityPortfolioRepository equityPortfolioRepository;
    @Autowired
    CompanyService companyService;
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
        equityPortfolioList.forEach(equityPortfolio -> {
            Company comp= companyService.getCompanyByNSECode(equityPortfolio.getStockSymbol());
            equityPortfolio.setCompany(comp);
            try {
                Stock stock = YahooFinance.get(equityPortfolio.getStockSymbol().trim()+ ".NS");
                BigDecimal price = stock.getQuote().getPrice();
                equityPortfolio.setLast_trading_price(price.floatValue());
                equityPortfolio.setCurrent_value(equityPortfolio.getLast_trading_price()*(equityPortfolio.getQuantity()));
                equityPortfolio.setInvested_Value(equityPortfolio.getAverage_cost()*equityPortfolio.getQuantity());
                equityPortfolio.setProfit_loss(equityPortfolio.getCurrent_value()-equityPortfolio.getInvested_Value());
                equityPortfolio.setNetPercentage_change((equityPortfolio.getProfit_loss()/equityPortfolio.getInvested_Value())*100);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        equityPortfolioRepository.saveAll(equityPortfolioList);
        return equityPortfolioList;
    }

    // Delete EquityPortfolio data by id
    public void delete(Long id) {
        EquityPortfolio equityPortfolio = findByID(id);
        equityPortfolioRepository.delete(equityPortfolio);
    }
}