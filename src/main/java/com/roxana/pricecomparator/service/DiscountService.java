package com.roxana.pricecomparator.service;

import com.roxana.pricecomparator.model.Discount;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        loadDiscounts("lidl", "2025-05-01");
        loadDiscounts("lidl", "2025-05-08");
        loadDiscounts("profi", "2025-05-01");
        loadDiscounts("profi", "2025-05-08");
        loadDiscounts("kaufland", "2025-05-01");
        loadDiscounts("kaufland", "2025-05-08");
    }

    private void loadDiscounts(String store, String date) {
        String fileName = store + "_discounts_" + date + ".csv";
        List<Discount> discounts = csvService.readDiscountsFromCsv(fileName);
        discountsByStore
                .computeIfAbsent(store.toLowerCase(), k -> new ArrayList<>())
                .addAll(discounts);
    }

    public Map<String, List<Discount>> getAllDiscounts() {
        return discountsByStore;
    }

    public List<Discount> getDiscountsForStore(String store) {
        return discountsByStore.get(store.toLowerCase());
    }
}
