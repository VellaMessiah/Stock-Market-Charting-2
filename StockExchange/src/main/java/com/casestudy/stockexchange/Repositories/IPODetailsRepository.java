package com.casestudy.stockexchange.Repositories;

import com.casestudy.stockexchange.Entity.IPODetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPODetailsRepository extends JpaRepository<IPODetails,Integer> {
}
