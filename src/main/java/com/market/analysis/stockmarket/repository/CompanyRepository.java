package com.market.analysis.stockmarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.market.analysis.stockmarket.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Query(value = "SELECT * FROM t_core_comapany_data m where m.nse_code is not null", nativeQuery = true)
	List<Company> getNSEcode();
}
