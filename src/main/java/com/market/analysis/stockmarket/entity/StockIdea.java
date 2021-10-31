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
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_stock_idea")
public class StockIdea {
    @Id
    private Long id;
    private String suggestedBy;
    private String infoSource;
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

}
