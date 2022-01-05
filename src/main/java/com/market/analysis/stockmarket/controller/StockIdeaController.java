package com.market.analysis.stockmarket.controller;

import com.market.analysis.stockmarket.entity.StockIdea;
import com.market.analysis.stockmarket.service.StockIdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class StockIdeaController {
    @Autowired
    private StockIdeaService stockResearchService;
    @PostMapping(value = "/saveStockList")

    public ResponseEntity<StockIdea> saveStockList(@RequestBody List<StockIdea> stockResearch){
        stockResearchService.saveStockResearchData(stockResearch);
        return new ResponseEntity<>((StockIdea) stockResearch,HttpStatus.CREATED);
    }
}
