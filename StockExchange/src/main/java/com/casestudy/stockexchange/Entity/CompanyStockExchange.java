package com.casestudy.stockexchange.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "company_stockexchange")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyStockExchange implements Serializable {
    /*@EmbeddedId
    private CompanyStockExchangeId companyStockExchangeId;*/

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    //@MapsId("company_id")
    private Company company;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stockexchange_id")
    //@MapsId("stockexchange_id")
    private StockExchange stockexchange;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private IPODetails ipoDetails;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_SE_id")
    private List<StockPrice> stockPrices = new ArrayList<StockPrice>();

    @Column(name = "company_code")
    private String companyCode;

   public CompanyStockExchange(Company company, StockExchange stockExchange, String companyCode) {
        this.company = company;
        this.stockexchange=stockExchange;
        this.companyCode=companyCode;
    }


    public boolean equals(Object o){
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CompanyStockExchange that = (CompanyStockExchange)o;
        return this.company.equals(that.stockexchange) && this.stockexchange.equals(that.stockexchange);
    }

    public int hashCode(){
        return Objects.hash(company,stockexchange);
    }
}
