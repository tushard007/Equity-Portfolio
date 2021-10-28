package com.market.analysis.stockmarket.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.market.analysis.stockmarket.Constants;
import com.market.analysis.stockmarket.entity.Company;
import com.market.analysis.stockmarket.service.CompanyService;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("api/v1")
public class CompanyController {
private static final Logger logger=  LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	CompanyService companyService;

	@GetMapping("/readCSVData")
	public void readCSVFile() throws IOException, CsvException {
		String fileName = Constants.COMPANY_CORE_DATA_CSV_PATH;
		List objCompanyData = new CsvToBeanBuilder(new FileReader(fileName)).withType(Company.class).build()
				.parse();
		saveCompanyData(objCompanyData);
		objCompanyData.forEach(System.out::println);
	}

	@PostMapping("/SaveCompanies")
	public void saveCompanyData(List<Company> cmp) {
		companyService.saveCompanyList(cmp);
	}

	@GetMapping("/GetNSEStockQuote")
	public void stockQuote() throws IOException {
		List<Company> cmp = companyService.getNSEcodeWithCompanyData();
		cmp.forEach(e -> {
			try {
				if ( !e.getNseCode().isEmpty()) {
					Stock stock = YahooFinance.get(e.getNseCode().trim() + ".NS");
					if (e.getNseCode() != null && stock.isValid())
						logger.info("Stock id:" + e.getStockId() + "\n");
					if (stock.getName() != null) {
						String cmpNameD = stock.getName();
						e.setCompanyName(cmpNameD);
					}
					stock.print();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
}
