package com.market.analysis.stockmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.market.analysis.stockmarket.entity.StockData;
import com.market.analysis.stockmarket.repository.StockDataRepository;

@Service
public class StockDataService {

	@Autowired
	StockDataRepository stockDataRepository;

	public ArrayList<StockData> save(ArrayList<StockData> stkData) {
		stockDataRepository.saveAll(stkData);
		return stkData;
	}
	
	public List<StockData> findAllStockData(){
	return	stockDataRepository.findAll();
	}

	public void deletedStockData(Long id){
		stockDataRepository.deleteById(id);
	}
}