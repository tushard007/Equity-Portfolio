package com.market.analysis.stockmarket.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.market.analysis.stockmarket.entity.Company;
import com.market.analysis.stockmarket.entity.YahooStockData;
import com.market.analysis.stockmarket.service.CompanyService;
import com.market.analysis.stockmarket.service.YahooStockDataService;

@RestController
@RequestMapping("api/v1")
public class YahooStockDataController {
	private static final Logger logger = LoggerFactory.getLogger(YahooStockDataController.class);

	@Autowired
	CompanyService companyService;
	@Autowired
	YahooStockDataService stockDataService;

	@PostMapping("/stockData")
	public ResponseEntity<List<YahooStockData>> saveStockQuote() {
		List<YahooStockData> lstStkData = stockDataService.findAllStockData();
		List<Company> cmp = companyService.getNSEcodeWithCompanyData();
		stockDataService.getLatestStockInfo(lstStkData, cmp);
		List<YahooStockData> updatedStkData = stockDataService.findAllStockData();
		logger.info("Total:%s, updatedStkData.size(), records available in database");
		return new ResponseEntity<>(updatedStkData, HttpStatus.CREATED);
	}

	@GetMapping("/stockData")
	public ResponseEntity<List<YahooStockData>> getStockQuote() {
		List<YahooStockData> lstStkData = stockDataService.findAllStockData();
		return new ResponseEntity<>(lstStkData, HttpStatus.OK);
	}

	@DeleteMapping("/stockData/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		stockDataService.deletedStockData(id);
		return new ResponseEntity<>("Stock Data is deleted successfully.!", HttpStatus.OK);
	}
}
