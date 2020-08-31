package com.casestudy.stockexchange.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stockexchange")
@EqualsAndHashCode
@Getter
@Setter
public class StockExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private int stockexchangeId;


    @EqualsAndHashCode.Include
    @Column(name = "stock_exchange")
    private String name;

    @EqualsAndHashCode.Exclude
    private String brief;

    @Column(name = "contact_address")
    @EqualsAndHashCode.Exclude
    private String contactAddress;

    @EqualsAndHashCode.Exclude
    private String remarks;

    @OneToMany(mappedBy = "stockexchange", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<CompanyStockExchange> companies;

}
