package com.casestudy.stockexchange.Service;

import com.casestudy.stockexchange.Entity.CompanyStockExchange;
import com.casestudy.stockexchange.Entity.StockExchange;
import com.casestudy.stockexchange.Entity.StockPrice;
import com.casestudy.stockexchange.Exception.IllegalRowException;
import com.casestudy.stockexchange.Repositories.CompanyStockExchangeRepository;
import com.casestudy.stockexchange.Repositories.StockPriceRepository;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UploadExcelService {
    private CompanyStockExchangeRepository companyStockExchangeRepository;
    private StockPriceRepository stockPriceRepository;

    @Autowired
    public UploadExcelService(CompanyStockExchangeRepository companyStockExchangeRepository, StockPriceRepository stockPriceRepository) {
        this.companyStockExchangeRepository = companyStockExchangeRepository;
        this.stockPriceRepository = stockPriceRepository;
    }

    public void uploadExcel(MultipartFile file) throws IOException, IllegalRowException, ParseException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        //Code to Check Excel File Data
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            StockPrice stockPrice = new StockPrice();
            XSSFRow row = worksheet.getRow(i);
            String companyCode = row.getCell(0).getStringCellValue().substring(0,6);
            Optional<CompanyStockExchange> optional = companyStockExchangeRepository.findByCompanyCode(companyCode);
            if(!optional.isPresent()) throw new IllegalRowException("No such company found with code : "+companyCode);
            CompanyStockExchange companyStockExchange = optional.get();
            stockPrice.setPrice(row.getCell(2).getNumericCellValue());
            stockPrice.setDate(row.getCell(3).getDateCellValue());
            stockPrice.setTime(new SimpleDateFormat("HH:mm:ss").parse(row.getCell(4).getStringCellValue()));
            stockPrice = stockPriceRepository.save(stockPrice);
            companyStockExchange.getStockPrices().add(stockPrice);
            //companyStockExchangeRepository.save(companyStockExchange);
        }
    }
}
