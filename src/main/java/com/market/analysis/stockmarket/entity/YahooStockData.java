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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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
	
}
