package com.market.analysis.stockmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.market.analysis.stockmarket.entity.Company;
import com.market.analysis.stockmarket.repository.CompanyRepository;

@Service
public class CompanyService {
	@Autowired
	CompanyRepository companyRepository;

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public List<Company> getNSEcodeWithCompanyData() {
		return companyRepository.getNSEcode();
	}

	public List<Company> save(List<Company> company) {
		companyRepository.saveAll(company);
		return company;
	}

}
