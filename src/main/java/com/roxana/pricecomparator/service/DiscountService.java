package com.roxana.pricecomparator.service;

import com.roxana.pricecomparator.model.Discount;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiscountService {
    private final CSVService csvService;
    private final Map<String, List<Discount>> discountsByStore = new HashMap<>();

    public DiscountService(CSVService csvService) {
        this.csvService = csvService;
    }

    @PostConstruct
    public void init() {
        discountsByStore.put("lidl", csvService.readDiscountsFromCsv("lidl_discounts_2025-05-01.csv"));
        discountsByStore.put("profi", csvService.readDiscountsFromCsv("profi_discounts_2025-05-01.csv"));
        discountsByStore.put("kaufland", csvService.readDiscountsFromCsv("kaufland_discounts_2025-05-01.csv"));
    }

    public Map<String, List<Discount>> getAllDiscounts() {
        return discountsByStore;
    }

    public List<Discount> getDiscountsForStore(String store) {
        return discountsByStore.get(store.toLowerCase());
    }
}
