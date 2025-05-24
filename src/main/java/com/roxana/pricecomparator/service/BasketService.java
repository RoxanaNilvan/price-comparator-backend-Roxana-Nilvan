package com.roxana.pricecomparator.service;

import com.roxana.pricecomparator.dto.*;
import com.roxana.pricecomparator.model.Discount;
import com.roxana.pricecomparator.model.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class BasketService {
    private final ProductService productService;
    private final DiscountService discountService;

    public BasketService(ProductService productService, DiscountService discountService) {
        this.productService = productService;
        this.discountService = discountService;
    }

    public BasketSplitResponseDTO optimizeSplit(List<BasketRequestItemDTO> items) {
        Map<String, List<Product>> allProducts = productService.getAllProducts();
        Map<String, List<Discount>> allDiscounts = discountService.getAllDiscounts();
        LocalDate today = LocalDate.of(2025, 5, 06); // data test

        List<BasketSplitProductDTO> results = new ArrayList<>();
        double total = 0.0;

        for (BasketRequestItemDTO item : items) {
            String productId = item.getProductId();
            int quantity = item.getQuantity();

            BasketSplitProductDTO best = null;
            double bestSubtotal = Double.MAX_VALUE;

            for (String store : allProducts.keySet()) {
                Optional<Product> match = allProducts.get(store).stream()
                        .filter(p -> p.getProductId().equals(productId))
                        .findFirst();

                if (match.isEmpty()) continue;

                Product product = match.get();
                Discount discount = allDiscounts.getOrDefault(store, List.of()).stream()
                        .filter(d -> d.getProductId().equals(productId))
                        .filter(d -> {
                            LocalDate from = LocalDate.parse(d.getFromDate());
                            LocalDate to = LocalDate.parse(d.getToDate());
                            return !today.isBefore(from) && !today.isAfter(to);
                        })
                        .findFirst()
                        .orElse(null);

                int discountPercent = discount != null ? discount.getPercentage() : 0;
                double finalPrice = product.getPrice() * (1 - discountPercent / 100.0);
                double subtotal = finalPrice * quantity;

                if (subtotal < bestSubtotal) {
                    best = new BasketSplitProductDTO(
                            productId,
                            store,
                            product.getProductName(),
                            product.getPrice(),
                            discountPercent,
                            finalPrice,
                            quantity,
                            subtotal
                    );
                    bestSubtotal = subtotal;
                }
            }

            if (best != null) {
                results.add(best);
                total += best.getSubtotal();
            } else {
                System.out.println("⚠️  Product with ID '" + productId + "' not found in any store for date " );
            }
        }

        return new BasketSplitResponseDTO(total, results);
    }
}
