package com.casestudy.stockexchange.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Getter
@Setter
public class Company {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int companyId;

    @Column(name = "company_name")
    @EqualsAndHashCode.Include
    private String companyName;

    @EqualsAndHashCode.Exclude
    private float turnover;

    @EqualsAndHashCode.Exclude
    private String ceo;


    @EqualsAndHashCode.Exclude
    private String description;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sector_id")
    @EqualsAndHashCode.Exclude
    private Sector sector;


   @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @EqualsAndHashCode.Exclude
    private List<CompanyStockExchange> listedInStockExchanges;



}
