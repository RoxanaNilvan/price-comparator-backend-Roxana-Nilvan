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

    public List<PriceHistoryResponseDTO> getPriceHistory(
            Optional<String> productId,
            Optional<String> store,
            Optional<String> category,
            Optional<String> brand
    ) {
        List<PriceHistoryResponseDTO> response = new ArrayList<>();

        List<String> allDates = List.of("2025-05-01", "2025-05-08");
        List<String> allStores = List.of("lidl", "profi", "kaufland");

        for (String s : allStores) {
            if (store.isPresent() && !store.get().equalsIgnoreCase(s)) continue;

            for (String date : allDates) {
                String fileName = s + "_" + date + ".csv";
                List<Product> products = csvService.readProductsFromCsv(fileName);

                for (Product p : products) {
                    if (productId.isPresent() && !p.getProductId().equalsIgnoreCase(productId.get())) continue;
                    if (category.isPresent() && !p.getProductCategory().equalsIgnoreCase(category.get())) continue;
                    if (brand.isPresent() && !p.getBrand().equalsIgnoreCase(brand.get())) continue;

                    PricePointDTO point = new PricePointDTO(date, p.getPrice());

                    Optional<PriceHistoryResponseDTO> existing = response.stream()
                            .filter(r -> r.getProductId().equals(p.getProductId()) && r.getStore().equalsIgnoreCase(s))
                            .findFirst();

                    if (existing.isPresent()) {
                        existing.get().getHistory().add(point);
                    } else {
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
