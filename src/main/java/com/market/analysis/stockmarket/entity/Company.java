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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.opencsv.bean.CsvBindByPosition;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_core_company_data")
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

}
