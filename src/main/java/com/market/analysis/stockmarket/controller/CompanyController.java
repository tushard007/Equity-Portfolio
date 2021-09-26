package com.market.analysis.stockmarket.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.analysis.stockmarket.entity.Company;
import com.market.analysis.stockmarket.service.CompanyService;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired(required = false)
	Company company;

	@Autowired
	CompanyService companyService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/readcsv")
	public void readCSVFile() throws IOException, CsvException {
		String fileName = "E:\\project\\screener data.csv";
		List<Company> lstCompanyData = new CsvToBeanBuilder(new FileReader(fileName)).withType(Company.class).build()
				.parse();
		saveCompanyData(lstCompanyData);
		lstCompanyData.forEach(System.out::println);
	}

	@PostMapping("/Company")
	public void saveCompanyData(List<Company> cmp) {
		companyService.save(cmp);
	}

	@GetMapping("/stockQuote")
	public void stockQuote() throws IOException {
		List<Company> cmp = companyService.getNSEcodeWithCompanyData();
		cmp.forEach(e -> {

			try {
				if (e.getStockId() > 1407 && !e.getNseCode().isEmpty()) {
					Stock stock = YahooFinance.get(e.getNseCode().trim() + ".NS");
					if (e.getNseCode().toString() != null && stock != null)
						System.out.print("Stock id:" + e.getStockId() + "\n");
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
