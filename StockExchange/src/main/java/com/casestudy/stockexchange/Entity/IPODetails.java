package com.casestudy.stockexchange.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IPODetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int pricePerShare;

    private int totalShares;

    @Temporal(TemporalType.DATE)
    private Date openedAt;

    private String remarks;

    /*
    1.	id
2.	Company Name
3.	Stock Exchange
4.	Price per share
5.	Total number of Shares
6.	Open Date Time

     */
}
