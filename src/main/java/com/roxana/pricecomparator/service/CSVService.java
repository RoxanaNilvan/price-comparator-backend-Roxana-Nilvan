package com.roxana.pricecomparator.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.roxana.pricecomparator.model.Discount;
import com.roxana.pricecomparator.model.Product;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class CSVService {
    public List<Product> readProductsFromCsv(String fileName) {
        try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("/data/" + fileName))) {
            return new CsvToBeanBuilder<Product>(reader)
                    .withType(Product.class)
                    .withSeparator(';')
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read product CSV: " + fileName, e);
        }
    }

    public List<Discount> readDiscountsFromCsv(String fileName) {
        try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("/data/" + fileName))) {
            return new CsvToBeanBuilder<Discount>(reader)
                    .withType(Discount.class)
                    .withSeparator(';')
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read discount CSV: " + fileName, e);
        }
    }
}
