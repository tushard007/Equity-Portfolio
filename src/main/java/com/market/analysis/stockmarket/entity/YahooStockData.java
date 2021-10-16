package com.market.analysis.stockmarket.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="t_yahoo_stock_data")
public class YahooStockData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stockDataId;
	private BigDecimal price;
	private	BigDecimal marketCap;
	private BigDecimal dividend ;
	private BigDecimal EPS;
	private BigDecimal PE;

	@CreationTimestamp
	private Date dateCreated;
	@Column(name = "timestamp")
	@UpdateTimestamp
	private Date timestamp;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="stock_Company_Id",
	referencedColumnName = "stock_Id")
	private Company company;
	public Company getCompany() {return company;}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Long getStockDataId() {
		return stockDataId;
	}
	public void setStockDataId(Long stockDataId) {
		this.stockDataId = stockDataId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(BigDecimal marketCap) {
		Integer divi=10000000;
		this.marketCap = marketCap.divideToIntegralValue(BigDecimal.valueOf(divi));
	}
	public BigDecimal getDividend() {
		return dividend;
	}
	public void setDividend(BigDecimal dividend) {
		this.dividend = dividend;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public BigDecimal getEPS() {return EPS;}
	public void setEPS(BigDecimal EPS) {this.EPS = EPS;}
	public BigDecimal getPE() {return PE;}
	public void setPE(BigDecimal PE) {this.PE = PE;}

	@Override
	public String toString() {
		return "YahooStockData{" +
				"stockDataId=" + stockDataId +
				", price=" + price +
				", marketCap=" + marketCap +
				", dividend=" + dividend +
				", EPS=" + EPS +
				", PE=" + PE +
				", dateCreated=" + dateCreated +
				", timestamp=" + timestamp +
				", company=" + company +
				'}';
	}
}
