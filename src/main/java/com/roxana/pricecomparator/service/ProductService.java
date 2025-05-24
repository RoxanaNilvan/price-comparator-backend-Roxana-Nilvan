package com.roxana.pricecomparator.service;

import com.roxana.pricecomparator.model.Discount;
import com.roxana.pricecomparator.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final CSVService csvService;

    public ProductService(CSVService csvService) {
        this.csvService = csvService;
    }

    private final Map<String, List<Product>> productsByStore = new HashMap<>();
    private final Map<String, List<Discount>> discountsByStore = new HashMap<>();

    @PostConstruct
    public void initData() {
        loadProducts("lidl", "2025-05-01");
        loadProducts("lidl", "2025-05-08");
        loadProducts("profi", "2025-05-01");
        loadProducts("profi", "2025-05-08");
        loadProducts("kaufland", "2025-05-01");
        loadProducts("kaufland", "2025-05-08");

        loadDiscounts("lidl", "2025-05-01");
        loadDiscounts("lidl", "2025-05-08");
        loadDiscounts("profi", "2025-05-01");
        loadDiscounts("profi", "2025-05-08");
        loadDiscounts("kaufland", "2025-05-01");
        loadDiscounts("kaufland", "2025-05-08");
    }

    private void loadProducts(String store, String date) {
        String file = store + "_" + date + ".csv";
        List<Product> list = csvService.readProductsFromCsv(file);
        productsByStore.computeIfAbsent(store, k -> new ArrayList<>()).addAll(list);
    }

    private void loadDiscounts(String store, String date) {
        String file = store + "_discounts_" + date + ".csv";
        List<Discount> list = csvService.readDiscountsFromCsv(file);
        discountsByStore.computeIfAbsent(store, k -> new ArrayList<>()).addAll(list);
    }

    public Map<String, List<Product>> getAllProducts() {
        return productsByStore;
    }

    public Map<String, List<Discount>> getAllDiscounts() {
        return discountsByStore;
    }
}
