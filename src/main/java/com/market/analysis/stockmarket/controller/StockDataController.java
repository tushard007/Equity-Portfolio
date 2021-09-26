package com.market.analysis.stockmarket.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.market.analysis.stockmarket.entity.Company;
import com.market.analysis.stockmarket.entity.StockData;
import com.market.analysis.stockmarket.service.CompanyService;
import com.market.analysis.stockmarket.service.StockDataService;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/Stock")
public class StockDataController {
	private static final Logger logger=  LoggerFactory.getLogger(StockDataController.class);

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
				if (!e.getNseCode().isEmpty()&&e.getStockId()>453) {
					Stock stock = YahooFinance.get(e.getNseCode().trim() + ".NS");
					if (e.getNseCode() != null && stock.isValid()&& stock!=null ) {
						//Created new object of company and saving information
						Company company = new Company();
						company.setStockId(e.getStockId());
						company.setCompanyName(e.getCompanyName());
						company.setBseCode(e.getBseCode());
						company.setNseCode(e.getNseCode());
						company.setIndustry(e.getIndustry());
						logger.debug("Stock id:" + e.getStockId() + "\n");

						StockData stockData = new StockData();
						BigDecimal price = stock.getQuote().getPrice();
						stockData.setPrice(price);
						BigDecimal change = stock.getQuote().getChangeInPercent();
						BigDecimal marketCap = stock.getStats().getMarketCap();
						if(marketCap!=null)
						stockData.setMarketCap(marketCap);
						BigDecimal dividend = stock.getDividend().getAnnualYield();
						stockData.setDividend(dividend);
						stockData.setCompany(company);
						logger.info("--------" + stock.getName().toString() + "-------");
						logger.info("Stock DB ID:"+e.getStockId()+" price:" + price + " " + "change:" + change + " " + "dividend:" + dividend
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
	
	@GetMapping("/GetStockInfo")
	public void getStockQuote() throws IOException {
	List<StockData> lstStkData=	stockDataService.findAllStockData();
		System.out.println("Stock data-"+lstStkData);
	}
}
