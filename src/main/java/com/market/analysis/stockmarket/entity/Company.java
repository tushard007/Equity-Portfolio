package com.market.analysis.stockmarket.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.opencsv.bean.CsvBindByPosition;

@Entity
@Table(name = "t_core_comapany_data")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@CsvBindByPosition(position = 0)
	@Column(name = "stock_Id", nullable = false)
	private Long stockId;
	@CsvBindByPosition(position = 0)
	@Column(name = "company_name", nullable = false)
	private String companyName;
	@CsvBindByPosition(position = 2)
	@Column(name = "nse_code")
	private String nseCode;
	@CsvBindByPosition(position = 1)
	@Column(name = "bse_code")
	private String bseCode;
	@CsvBindByPosition(position = 3)
	@Column(name = "industry")
	private String industry;
	@CreationTimestamp
	private Date dateCreated;
	@Column(name = "timestamp")
	@UpdateTimestamp
	private Date timestamp;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getNseCode() {
		return nseCode;
	}

	public void setNseCode(String nseCode) {
		this.nseCode = nseCode;
	}

	public String getBseCode() {
		return bseCode;
	}

	public void setBseCode(String bseCode) {
		this.bseCode = bseCode;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Override
	public String toString() {
		return "Company [stockId=" + stockId + ", companyName=" + companyName + ", nseCode=" + nseCode + ", bseCode="
				+ bseCode + ", industry=" + industry + "]";
	}

}
