package com.market.analysis.stockmarket.controller;

import java.io.IOException;
import java.math.BigDecimal;
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

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("api/v1")
public class YahooStockDataController {
	private static final Logger logger=  LoggerFactory.getLogger(YahooStockDataController.class);

	@Autowired
	CompanyService companyService;
	@Autowired
	YahooStockDataService stockDataService;

	@PostMapping("/stockData")
	public ResponseEntity <ArrayList<YahooStockData>> saveStockQuote() throws IOException {
		List<YahooStockData> lstStkData=stockDataService.findAllStockData();
		ArrayList<YahooStockData> lstYahooStockData = new ArrayList<YahooStockData>();
		
		if(lstStkData.isEmpty()) {
			List<Company> cmp = companyService.getNSEcodeWithCompanyData();
			//Long lastQuoteId=stockDataService.getLastSockQuote();
			cmp.forEach(e -> {
				try {
					if (!e.getNseCode().isEmpty()/*&& e.getStockId()>lastQuoteId*/) {
						Stock stock = YahooFinance.get(e.getNseCode().trim() + ".NS");
						if (e.getNseCode() != null && stock != null) {
							//Created new object of company and saving information
							Company company = new Company();
							company.setStockId(e.getStockId());
							company.setCompanyName(e.getCompanyName());
							company.setBseCode(e.getBseCode());
							company.setNseCode(e.getNseCode());
							company.setIndustry(e.getIndustry());
							logger.debug("Stock id:" + e.getStockId() + "\n");

							YahooStockData yahooStockData = new YahooStockData();
							BigDecimal price = stock.getQuote().getPrice();
							yahooStockData.setPrice(price);
							BigDecimal marketCap = stock.getStats().getMarketCap();
							if (marketCap != null)
								yahooStockData.setMarketCap(marketCap);
							BigDecimal dividend = stock.getDividend().getAnnualYield();
							yahooStockData.setDividend(dividend);
							BigDecimal EPS = stock.getStats().getEps();
							yahooStockData.setEPS(EPS);
							yahooStockData.setCompany(company);
							BigDecimal pe = stock.getStats().getPe();
							yahooStockData.setPE(pe);
							lstYahooStockData.add(yahooStockData);

							logger.info("--------" + stock.getName() + "-------");
							logger.info("Stock DB ID:" + e.getStockId() + " price:" + price + " " + "dividend:" + dividend + " " + "marketCap:" + marketCap);
							logger.info("EPS:" + stock.getStats().getEps() + " PE:" +
									stock.getStats().getPe());
							logger.info("**************************************");

						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			stockDataService.save(lstYahooStockData);
		}
		return new ResponseEntity<ArrayList<YahooStockData>> (lstYahooStockData, HttpStatus.CREATED);
	}
	
	@GetMapping("/stockData")
	public ResponseEntity <List<YahooStockData>> getStockQuote() throws IOException {
	List<YahooStockData> lstStkData=	stockDataService.findAllStockData();
		System.out.println("Stock data-"+lstStkData);
		return new ResponseEntity<List<YahooStockData>> (lstStkData, HttpStatus.OK);
	}

	@DeleteMapping("/stockData/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		stockDataService.deletedStockData(id);
		return new ResponseEntity<String>("Stock Data is deleted successfully.!", HttpStatus.OK);
	}
}
