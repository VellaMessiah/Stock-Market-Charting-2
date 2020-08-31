package com.casestudy.stockexchange.Repositories;

import com.casestudy.stockexchange.Entity.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StockPriceRepository extends JpaRepository<StockPrice,Integer> {
}
