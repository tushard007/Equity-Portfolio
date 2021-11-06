package com.market.analysis.stockmarket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.market.analysis.stockmarket.entity.Company;
import com.market.analysis.stockmarket.entity.YahooStockData;
import com.market.analysis.stockmarket.repository.YahooStockDataRepository;

@Service
public class YahooStockDataService {
	private static final Logger logger = LoggerFactory.getLogger(YahooStockDataService.class);
	@Autowired
	YahooStockDataRepository stockDataRepository;

	public ArrayList<YahooStockData> saveStockList(ArrayList<YahooStockData> stkData) {
		stockDataRepository.saveAll(stkData);
		return stkData;
	}

	public ArrayList<YahooStockData> findAllStockData() {
		return (ArrayList<YahooStockData>) stockDataRepository.findAll();
	}

	public void deletedStockData(Long id) {
		stockDataRepository.deleteById(id);
	}

	public Long getLastSockQuote() {
		return stockDataRepository.getStockQuoteTableLastRow();
	}

	// generic method to get stock Quote data from yahoo finance
	public void getLatestStockInfo(ArrayList<YahooStockData> lstStkData, List<Company> cmp) {
		if (lstStkData.size() > 0) {
			lstStkData.forEach(l -> {
				try {
						Stock stock = YahooFinance.get(l.getCompany().getNseCode().trim()+ ".NS");
						if (l.getCompany().getNseCode() != null && stock != null) {
							l.getCompany().setStockId(l.getCompany().getStockId());
							l.getCompany().setCompanyName(l.getCompany().getCompanyName());
							l.getCompany().setBseCode(l.getCompany().getBseCode());
							l.getCompany().setNseCode(l.getCompany().getNseCode());
							l.getCompany().setIndustry(l.getCompany().getIndustry());

							BigDecimal price = stock.getQuote().getPrice();
							l.setPrice(price);
							BigDecimal marketCap = stock.getStats().getMarketCap();
							if (marketCap != null)
							l.setMarketCap(marketCap.divideToIntegralValue(BigDecimal.valueOf(10000000)));
							BigDecimal dividend = stock.getDividend().getAnnualYield();
							l.setDividend(dividend);
							BigDecimal EPS = stock.getStats().getEps();
							l.setEPS(EPS);
							l.setCompany(l.getCompany());
							BigDecimal pe = stock.getStats().getPe();
							l.setPE(pe);

							logger.info("--------" + stock.getName() + "-------");
							logger.info("Stock DB ID:" + l.getCompany().getStockId() + " price:" + price + " " + "dividend:"
									+ dividend + " " + "marketCap:" + marketCap);
							logger.info("EPS:" + stock.getStats().getEps() + " PE:" + stock.getStats().getPe());
							logger.info("**************************************");
							stockDataRepository.save(l);
						}
						logger.info("Stock quote data updated successfully");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} else {
			ArrayList<YahooStockData> lstYahooStockData = new ArrayList<YahooStockData>();
			cmp.forEach(e -> {
				try {
					if (!e.getNseCode().isEmpty()) {
						Stock stock = YahooFinance.get(e.getNseCode().trim() + ".NS");
						if (e.getNseCode() != null && stock != null) {
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
							yahooStockData.setMarketCap(marketCap.divideToIntegralValue(BigDecimal.valueOf(10000000)));
							BigDecimal dividend = stock.getDividend().getAnnualYield();
							yahooStockData.setDividend(dividend);
							BigDecimal EPS = stock.getStats().getEps();
							yahooStockData.setEPS(EPS);
							yahooStockData.setCompany(company);
							BigDecimal pe = stock.getStats().getPe();
							yahooStockData.setPE(pe);
							lstYahooStockData.add(yahooStockData);

							logger.info("**********" + stock.getName() + "***********");
							logger.info("Stock DB ID:" + e.getStockId() + " price:" + price + " " + "dividend:"
									+ dividend + " " + "marketCap:" + marketCap);
							logger.info("EPS:" + stock.getStats().getEps() + " PE:" + stock.getStats().getPe());
							logger.info("**************************************");
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			stockDataRepository.saveAll(lstYahooStockData);
			logger.info("Stock quote data inserted successfully");
		}
	}
}