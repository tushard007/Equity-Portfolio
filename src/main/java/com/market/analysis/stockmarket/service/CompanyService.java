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

    //get all data
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    //save perticular data
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    //save all list
    public List<Company> saveCompanyList(List<Company> company) {
        companyRepository.saveAll(company);
        return company;
    }

    public Company getCompanyByID(Long id)
    {
        return companyRepository.getById(id);
    }
    public List<Company> getNSEcodeWithCompanyData() {
        return companyRepository.getNSEcode();
    }
}
