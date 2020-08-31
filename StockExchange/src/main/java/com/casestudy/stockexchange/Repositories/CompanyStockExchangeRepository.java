package com.casestudy.stockexchange.Repositories;

import com.casestudy.stockexchange.Entity.CompanyStockExchange;
import com.casestudy.stockexchange.Entity.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyStockExchangeRepository extends JpaRepository<CompanyStockExchange, Integer> {
    Optional<CompanyStockExchange> findByCompanyCode(String companyCode);
}
