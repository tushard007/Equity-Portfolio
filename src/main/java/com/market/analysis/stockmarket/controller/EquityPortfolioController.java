package com.market.analysis.stockmarket.controller;

import java.util.List;

import com.market.analysis.stockmarket.entity.EquityPortfolio;
import com.market.analysis.stockmarket.service.EquityPortfolioService;
import com.market.analysis.stockmarket.service.YahooStockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class EquityPortfolioController {
    @Autowired
    YahooStockDataService stockDataService;
    @Autowired
    EquityPortfolioService equityPortfolioService;

    @GetMapping("/EquityPortfolio")
    public ResponseEntity<List<EquityPortfolio>> getEquityPortfolio() {
        List<EquityPortfolio> lstEqPortfolio = equityPortfolioService.findAll();
        return new ResponseEntity<List<EquityPortfolio>>(lstEqPortfolio, HttpStatus.OK);
    }

    @PostMapping("/EquityPortfolio")
    public ResponseEntity<EquityPortfolio> saveEquityPortfolio(@RequestBody EquityPortfolio equityPortfolio) {
        EquityPortfolio objEquityPortfolio= equityPortfolioService.save(equityPortfolio);
        return new ResponseEntity<EquityPortfolio>(objEquityPortfolio, HttpStatus.CREATED);
    }

    @DeleteMapping("/EquityPortfolio/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable("id") Long id){
        equityPortfolioService.delete(id);
        return new ResponseEntity<String>("Stock has been deleted successfully",HttpStatus.OK);
    }
}
