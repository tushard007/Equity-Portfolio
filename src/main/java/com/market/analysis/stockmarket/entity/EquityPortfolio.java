package com.market.analysis.stockmarket.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="t_equity_portfolio")
public class EquityPortfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ep_id;
    private Integer quantity;
    private float average_cost;
    private float last_trading_price;
    private float current_value;
    private float profit_loss;
    private float net_change;
    private String broker;
    private String category;
    private float dividend;

    //One to One Mapping with t_core_company_data table
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumns
    ({
        @JoinColumn(name="stock_Company_Id", referencedColumnName="stock_Id"),
        @JoinColumn(name="Stock_company_name", referencedColumnName="company_name")
    })
    private Company company;
    @CreationTimestamp
    private Date dateCreated;
    @Column(name = "timestamp")
    @UpdateTimestamp
    private Date timestamp;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public float getAverage_cost() {
        return average_cost;
    }

    public void setAverage_cost(float average_cost) {
        this.average_cost = average_cost;
    }

    public float getLast_trading_price() {
        return last_trading_price;
    }

    public void setLast_trading_price(float last_trading_price) {
        this.last_trading_price = last_trading_price;
    }

    public float getCurrent_value() {
        return current_value;
    }

    public void setCurrent_value(float current_value) {
        this.current_value = current_value;
    }

    public float getProfit_loss() {
        return profit_loss;
    }

    public void setProfit_loss(float profit_loss) {
        this.profit_loss = profit_loss;
    }

    public float getNet_change() {
        return net_change;
    }

    public void setNet_change(float net_change) {
        this.net_change = net_change;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getDividend() {
        return dividend;
    }

    public void setDividend(float dividend) {
        this.dividend = dividend;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    @Override
    public String toString() {
        return "EquityPortfolio{" +
                "ep_id=" + ep_id +
                ", quantity=" + quantity +
                ", average_cost=" + average_cost +
                ", last_trading_price=" + last_trading_price +
                ", current_value=" + current_value +
                ", profit_loss=" + profit_loss +
                ", net_change=" + net_change +
                ", broker='" + broker + '\'' +
                ", category='" + category + '\'' +
                ", dividend=" + dividend +
                ", company=" + company +
                ", dateCreated=" + dateCreated +
                ", timestamp=" + timestamp +
                '}';
    }
}
