package com.market.analysis.stockmarket.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "t_stock_idea")
public class StockIdea {
    @Id
    private Long id;
    private String suggestedBy;
    private String InfoSource;
    private String theme;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="stock_Company_Id",
            referencedColumnName = "stock_Id")
    private Company company;
    private String comments;

    @CreationTimestamp
    private Date dateCreated;
    @Column(name = "timestamp")
    @UpdateTimestamp
    private Date timestamp;



    public String getSuggestedBy() {
        return suggestedBy;
    }

    public void setSuggestedBy(String suggestedBy) {
        this.suggestedBy = suggestedBy;
    }

    public String getInfoSource() {
        return InfoSource;
    }

    public void setInfoSource(String infoSource) {
        InfoSource = infoSource;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    public Company getCompany() {return company;}
    public void setCompany(Company company) {
        this.company = company;
    }

}
