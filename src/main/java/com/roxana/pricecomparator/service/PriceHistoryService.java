package com.roxana.pricecomparator.service;

import com.roxana.pricecomparator.dto.PriceHistoryResponseDTO;
import com.roxana.pricecomparator.dto.PricePointDTO;
import com.roxana.pricecomparator.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PriceHistoryService {
    private final CSVService csvService;

    public PriceHistoryService(CSVService csvService) {
        this.csvService = csvService;
    }

    /**
     * Retrieves the price history of products across different stores and dates.
     * Filters can be applied by product ID, store name, category, or brand.
     *
     * @param productId Optional filter to match a specific product by ID
     * @param store Optional filter to select a specific store
     * @param category Optional filter for product category (e.g., lactate)
     * @param brand Optional filter for brand (e.g., Zuzu)
     * @return A list of price history entries per product and store
     */
    public List<PriceHistoryResponseDTO> getPriceHistory(
            Optional<String> productId,
            Optional<String> store,
            Optional<String> category,
            Optional<String> brand
    ) {
        List<PriceHistoryResponseDTO> response = new ArrayList<>();

        // Define available time snapshots and supported stores
        List<String> allDates = List.of("2025-05-01", "2025-05-08");
        List<String> allStores = List.of("lidl", "profi", "kaufland");

        for (String s : allStores) {
            // Skip stores that don't match the filter (if one is provided)
            if (store.isPresent() && !store.get().equalsIgnoreCase(s)) continue;

            for (String date : allDates) {
                // Construct file name dynamically and read product data
                String fileName = s + "_" + date + ".csv";
                List<Product> products = csvService.readProductsFromCsv(fileName);

                for (Product p : products) {
                    // Apply filtering logic on productId, category, and brand
                    if (productId.isPresent() && !p.getProductId().equalsIgnoreCase(productId.get())) continue;
                    if (category.isPresent() && !p.getProductCategory().equalsIgnoreCase(category.get())) continue;
                    if (brand.isPresent() && !p.getBrand().equalsIgnoreCase(brand.get())) continue;

                    // Create a new data point for this product's price on the current date
                    PricePointDTO point = new PricePointDTO(date, p.getPrice());

                    // Check if this product already has an entry in the response for this store
                    Optional<PriceHistoryResponseDTO> existing = response.stream()
                            .filter(r -> r.getProductId().equals(p.getProductId()) && r.getStore().equalsIgnoreCase(s))
                            .findFirst();

                    if (existing.isPresent()) {
                        // If found, just append the price point to its history
                        existing.get().getHistory().add(point);
                    } else {
                        // Otherwise, create a new response DTO with the first price point
                        List<PricePointDTO> history = new ArrayList<>();
                        history.add(point);
                        response.add(new PriceHistoryResponseDTO(
                                p.getProductId(),
                                p.getProductName(),
                                s,
                                p.getProductCategory(),
                                p.getBrand(),
                                history
                        ));
                    }
                }
            }
        }

        return response;
    }
}
