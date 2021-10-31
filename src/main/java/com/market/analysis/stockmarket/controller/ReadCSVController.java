package com.market.analysis.stockmarket.controller;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.market.analysis.stockmarket.Constants;
import com.market.analysis.stockmarket.entity.Company;
import com.market.analysis.stockmarket.entity.EquityPortfolio;
import com.market.analysis.stockmarket.service.CompanyService;
import com.market.analysis.stockmarket.service.EquityPortfolioService;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.exceptions.CsvException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ReadCSVController reference example:
 * https://sunitc.dev/2020/05/31/read-csv-file-to-java-bean-using-open-csv/
 */

@RestController
@RequestMapping("v1/read_csv")
public class ReadCSVController {
	private static final Logger logger = LoggerFactory.getLogger(ReadCSVController.class);
	@Autowired
	CompanyService companyService;
	@Autowired
	EquityPortfolioService equityPortfolioService;

	@PostMapping("/company_core_data")
	public void readCompanyCSVFile() throws IOException, CsvException {
		Reader reader = Files.newBufferedReader(Paths.get(Constants.COMPANY_CORE_DATA_CSV_PATH));
		CsvToBean<Company> objCompanyData = new CsvToBeanBuilder(reader).withType(Company.class)
				.withIgnoreLeadingWhiteSpace(true).build();
		List<Company> companyList = objCompanyData.parse();
		companyService.saveCompanyList(companyList);
		logger.info("Company CSV file read successfully");

	}

	@PostMapping("/stock_Portfolio_data")
	public void readEqPortfolioCSVFile() throws IOException, CsvException {
		Reader reader = Files.newBufferedReader(Paths.get(Constants.EQUITY_PORTFOLIO_CSV_PATH));
		CsvToBean<EquityPortfolio> eqPortfolio = new CsvToBeanBuilder(reader).withType(EquityPortfolio.class)
				.withSkipLines(1).withIgnoreLeadingWhiteSpace(true).build();
		List<EquityPortfolio> eqPortfolioList = eqPortfolio.parse();
		equityPortfolioService.SaveAllList(eqPortfolioList);
		logger.info("Company CSV file read successfully");

	}
}
