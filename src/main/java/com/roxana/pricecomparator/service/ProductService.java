package com.roxana.pricecomparator.service;

import com.roxana.pricecomparator.model.Discount;
import com.roxana.pricecomparator.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final CSVService csvService;

    private final Map<String, List<Product>> productsByStore = new HashMap<>();
    private final Map<String, List<Discount>> discountsByStore = new HashMap<>();

    public ProductService(CSVService csvService) {
        this.csvService = csvService;
    }

    @PostConstruct
    public void initData() {
        productsByStore.put("lidl", csvService.readProductsFromCsv("lidl_2025-05-08.csv"));
        productsByStore.put("profi", csvService.readProductsFromCsv("profi_2025-05-08.csv"));
        productsByStore.put("kaufland", csvService.readProductsFromCsv("kaufland_2025-05-08.csv"));

        discountsByStore.put("lidl", csvService.readDiscountsFromCsv("lidl_discounts_2025-05-08.csv"));
        discountsByStore.put("profi", csvService.readDiscountsFromCsv("profi_discounts_2025-05-08.csv"));
        discountsByStore.put("kaufland", csvService.readDiscountsFromCsv("kaufland_discounts_2025-05-08.csv"));
    }

    public Map<String, List<Product>> getAllProducts() {
        return productsByStore;
    }

    public Map<String, List<Discount>> getAllDiscounts() {
        return discountsByStore;
    }
}
