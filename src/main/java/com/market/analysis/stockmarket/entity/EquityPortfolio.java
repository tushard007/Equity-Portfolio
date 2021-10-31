package com.market.analysis.stockmarket.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

import com.opencsv.bean.CsvBindByPosition;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="t_equity_portfolio")
public class EquityPortfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ep_id;
    @CsvBindByPosition(position = 1)
    private Integer quantity;
    @CsvBindByPosition(position = 2)
    private float average_cost;
    private float last_trading_price;
    private float current_value;
    private float profit_loss;
    private float net_change;
    @CsvBindByPosition(position = 3)
    private String broker;
    @CsvBindByPosition(position = 4)
    private String category;
    @CsvBindByPosition(position = 5)
    private float dividend;
    @Transient
    private Long stock_Id_ref;
    @Transient
    @CsvBindByPosition(position = 0)
    private String stockSymbol;
    //One to One Mapping with t_core_company_data table
    @OneToOne(targetEntity =Company.class,cascade = CascadeType.MERGE)
	@JoinColumn(name="stock_Company_Id",
	referencedColumnName = "stock_Id")
	private Company company;
    @CreationTimestamp
    private Date dateCreated;
    @Column(name = "timestamp")
    @UpdateTimestamp
    private Date timestamp;

}
