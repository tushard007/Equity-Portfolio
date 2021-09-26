package com.market.analysis.stockmarket.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.market.analysis.stockmarket.entity.Company;
import com.market.analysis.stockmarket.entity.StockData;
import com.market.analysis.stockmarket.service.CompanyService;
import com.market.analysis.stockmarket.service.StockDataService;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController

public class StockDataController {

	@Autowired
	CompanyService companyService;
	@Autowired
	StockDataService stockDataService;

	@PostMapping("/SaveQuote")
	public void saveStockQuote() throws IOException {
		List<Company> cmp = companyService.getNSEcodeWithCompanyData();
		ArrayList<StockData> lstStockData = new ArrayList<StockData>();
		cmp.forEach(e -> {
			try {
				if (!e.getNseCode().isEmpty()) {
					Stock stock = YahooFinance.get(e.getNseCode().trim() + ".NS");
					if (e.getNseCode().toString() != null && stock != null) {
						StockData stockData = new StockData();
						Company company = new Company();
						company.setStockId(e.getStockId());
						company.setCompanyName(e.getCompanyName());
						company.setBseCode(e.getBseCode());
						company.setNseCode(e.getNseCode());
						company.setIndustry(e.getIndustry());
						stockData.setCompany(company);

						System.out.print("Stock id:" + e.getStockId() + "\n");

						BigDecimal price = stock.getQuote().getPrice();
						stockData.setPrice(price);
						BigDecimal change = stock.getQuote().getChangeInPercent();
						BigDecimal marketCap = stock.getStats().getMarketCap();
						stockData.setMarketCap(marketCap);
						BigDecimal dividend = stock.getDividend().getAnnualYield();
						stockData.setDividend(dividend);

						System.out.println("--------" + stock.getName().toString() + "-------");
						System.out.println("price:" + price + " " + "change:" + change + " " + "dividend:" + dividend
								+ " " + "marketCap:" + marketCap);
						lstStockData.add(stockData);
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				stockDataService.save(lstStockData);
			}
		});
	}
	
	@GetMapping("/GetQuote")
	public void getStockQuote() throws IOException {
	List<StockData> lstStkData=	stockDataService.findAllStockData();
		System.out.println("Stock data-"+lstStkData);
	}
}
