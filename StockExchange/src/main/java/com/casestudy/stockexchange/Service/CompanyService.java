package com.casestudy.stockexchange.Service;

import com.casestudy.stockexchange.Entity.Company;
import com.casestudy.stockexchange.Entity.IPODetails;
import com.casestudy.stockexchange.Entity.StockExchange;
import com.casestudy.stockexchange.Entity.StockPrice;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    public Optional<Company> findById(int code);
    public Company addCompany(Company c);
    public List<Company> findAllCompanies();
    public List<StockExchange> findStockExchangeByCompanyId(Integer id);
    public List<IPODetails> findIPODetailsByCompanyId(Integer id);
    public List<IPODetails> findIPODetailsByCompany(Company company);


}
