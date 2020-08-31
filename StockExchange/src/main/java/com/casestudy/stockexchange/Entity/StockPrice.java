package com.casestudy.stockexchange.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double price;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.TIME)
    private Date time;
    /*
    1.	Company Code
2.	Stock Exchange
3.	Current Price
4.	Date
5.	Time

    */


}
