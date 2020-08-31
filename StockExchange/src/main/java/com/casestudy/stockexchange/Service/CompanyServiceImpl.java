package com.casestudy.stockexchange.Service;

import com.casestudy.stockexchange.Entity.*;
import com.casestudy.stockexchange.Repositories.CompanyRepository;
import com.casestudy.stockexchange.Repositories.StockExchangeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    ModelMapper modelMapper;

    private CompanyRepository companyRepository;
    private StockExchangeRepository stockExchangeRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, StockExchangeRepository stockExchangeRepository) {
        this.companyRepository = companyRepository;
        this.stockExchangeRepository = stockExchangeRepository;
    }

    @Override
    public Optional<Company> findById(int code) {
        Optional<Company> company = companyRepository.findById(code);
        if(company.isPresent())
            return company;
        return null;
    }

    @Override
    public Company addCompany(Company c) {
        Company company = companyRepository.save(c);
        return company;
    }


    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public List<StockExchange> findStockExchangeByCompanyId(Integer id){
        Optional<Company> optional = companyRepository.findById(id);
        if(optional.isPresent()){
            Company company = optional.get();
            List<CompanyStockExchange> joinTableEntt = company.getListedInStockExchanges();
            List<StockExchange> stockExchangeList = new ArrayList<StockExchange>();
            for(CompanyStockExchange c: joinTableEntt){
                stockExchangeList.add(c.getStockexchange());
            }
            return  stockExchangeList;
        }
        return null;
    }

    @Override
    public List<IPODetails> findIPODetailsByCompanyId(Integer id) {
        Optional<Company> optional = companyRepository.findById(id);
        if(optional.isPresent()){
            Company company = optional.get();
            return findIPODetailsByCompany(company);
        }
        return null;
    }

    @Override
    public List<IPODetails> findIPODetailsByCompany(Company company) {
        List<CompanyStockExchange> list = company.getListedInStockExchanges();
        List<IPODetails> ipoList = new ArrayList<IPODetails>();
        for(CompanyStockExchange cse : list){
            ipoList.add(cse.getIpoDetails());
        }
        return ipoList;
    }



}
