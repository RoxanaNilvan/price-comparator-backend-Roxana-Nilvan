package com.roxana.pricecomparator.service;

import com.roxana.pricecomparator.model.Discount;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    /**
     * Retrieves all discounts that have been added recently,
     * meaning their start date is today or yesterday.
     *
     * This simulates a 24-hour "new discounts" feed.
     *
     * @return A sorted list of new discounts across all stores
     */
    public List<Discount> getNewDiscounts() {
        // Define today's and yesterday's date for the comparison window
        LocalDate today = LocalDate.of(2025, 5, 07); //  test date
        LocalDate yesterday = today.minusDays(1);

        return discountsByStore.values().stream()
                .flatMap(List::stream) // flatten all discounts from all stores
                .filter(d -> {
                    // Parse discount start date and check if it's recent
                    LocalDate from = LocalDate.parse(d.getFromDate());
                    return from.equals(today) || from.equals(yesterday);
                })
                .sorted((a, b) -> b.getFromDate().compareTo(a.getFromDate())) // newest first
                .toList();
    }

    public List<Discount> getTopDiscounts(int limit) {
        LocalDate today = LocalDate.of(2025, 5, 07);
        return discountsByStore.values().stream()
                .flatMap(List::stream)
                .filter(d -> {
                    LocalDate from = LocalDate.parse(d.getFromDate());
                    LocalDate to = LocalDate.parse(d.getToDate());
                    return !today.isBefore(from) && !today.isAfter(to);
                })
                .sorted((a, b) -> Integer.compare(b.getPercentage(), a.getPercentage()))
                .limit(limit)
                .toList();
    }
}
