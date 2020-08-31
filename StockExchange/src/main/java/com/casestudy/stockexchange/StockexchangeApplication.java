package com.casestudy.stockexchange;

import com.casestudy.stockexchange.Entity.Company;
import com.casestudy.stockexchange.Entity.CompanyStockExchange;
import com.casestudy.stockexchange.Entity.StockExchange;
import com.casestudy.stockexchange.Repositories.CompanyStockExchangeRepository;
import com.casestudy.stockexchange.Service.CompanyService;
import com.casestudy.stockexchange.Service.StockExchangeService;
import com.casestudy.stockexchange.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.casestudy.stockexchange")
public class StockexchangeApplication implements CommandLineRunner {

    @Autowired
    UserService userService;


    @Autowired
    CompanyService companyService;

    @Autowired
    StockExchangeService stockExchangeService;

    @Autowired
    CompanyStockExchangeRepository companyStockExchangeRepository;

    public static void main(String[] args)  {
        SpringApplication.run(StockexchangeApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {


        Company c1 = companyService.findById(500112).get();
        if(c1==null)
        {
            System.out.println("NULL COMPANY");
            return;
        }
        StockExchange stockExchange = stockExchangeService.findById(1);
        if(stockExchange==null)
        {
            System.out.println("NULL SE");
            return;
        }
        stockExchangeService.addCompanyToStockExchange(c1,stockExchange,"500112");
        List<Company> companies = stockExchangeService.getCompaniesByStockexchangeId(1);
        if(companies.isEmpty())
            System.out.println("LIST EMPTY");
        for(Company c: companies){
            System.out.println("NAME"+c.getCompanyName());

        }
        List<StockExchange> list = companyService.findStockExchangeByCompanyId(500112);

        if(list.isEmpty())
            System.out.println(" SE LIST EMPTY");
        for(StockExchange c: list){
            System.out.println("NAME"+c.getName());
        }

        //Optional<CompanyStockExchange> optional = companyStockExchangeRepository.findByCompanyCode("500112");
        //if(optional.isPresent()){System.out.println("FOUND");}





    }

    @Bean
    ModelMapper getModelMapper(){return new ModelMapper();}

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

}
