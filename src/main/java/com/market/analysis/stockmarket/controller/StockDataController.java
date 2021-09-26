package com.market.analysis.stockmarket.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
				if (!e.getNseCode().isEmpty()) {
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
						BigDecimal marketCap = stock.getStats().getMarketCap();
						if(marketCap!=null)
						stockData.setMarketCap(marketCap);
						BigDecimal dividend = stock.getDividend().getAnnualYield();
						stockData.setDividend(dividend);
						BigDecimal EPS=stock.getStats().getEps();
						stockData.setEPS(EPS);
						stockData.setCompany(company);
						BigDecimal pe=stock.getStats().getPe();
						stockData.setPE(pe);
						lstStockData.add(stockData);

						logger.info("--------" + stock.getName() + "-------");
						logger.info("Stock DB ID:"+e.getStockId()+" price:" + price +" " + "dividend:" + dividend + " " + "marketCap:" + marketCap);
						logger.info("EPS:"+stock.getStats().getEps()+" PE:"+
								stock.getStats().getPe());
						logger.info("**************************************");
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
