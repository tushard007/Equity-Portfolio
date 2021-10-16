package com.market.analysis.stockmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.market.analysis.stockmarket.entity.YahooStockData;
import com.market.analysis.stockmarket.repository.YahooStockDataRepository;

@Service
public class YahooStockDataService {

	@Autowired
    YahooStockDataRepository stockDataRepository;

	public ArrayList<YahooStockData> save(ArrayList<YahooStockData> stkData) {
		stockDataRepository.saveAll(stkData);
		return stkData;
	}
	
	public List<YahooStockData> findAllStockData(){
	return	stockDataRepository.findAll();
	}

	public void deletedStockData(Long id){
		stockDataRepository.deleteById(id);
	}

	public Long getLastSockQuote(){
	return	stockDataRepository.getStockQuoteTableLastRow();	
	}
}