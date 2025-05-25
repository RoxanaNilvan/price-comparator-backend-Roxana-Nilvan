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

    /**
     * Finds the best store for each product in the user's shopping basket,
     * selecting the store that offers the lowest total price after applying
     * valid discounts for the current date.
     *
     * @param items List of items the user wants to purchase (productId + quantity)
     * @return A response with the optimal split per product and total cost
     */
    public BasketSplitResponseDTO optimizeSplit(List<BasketRequestItemDTO> items) {
        // Load all product and discount data from CSV for all stores
        Map<String, List<Product>> allProducts = productService.getAllProducts();
        Map<String, List<Discount>> allDiscounts = discountService.getAllDiscounts();

        // Define the current date for which discounts are evaluated (can be now() in prod)
        LocalDate today = LocalDate.of(2025, 5, 06); // test date

        List<BasketSplitProductDTO> results = new ArrayList<>();
        double total = 0.0;

        // Loop over each product in the basket
        for (BasketRequestItemDTO item : items) {
            String productId = item.getProductId();
            int quantity = item.getQuantity();

            BasketSplitProductDTO best = null;
            double bestSubtotal = Double.MAX_VALUE;

            // Check which store offers this product at the best discounted price
            for (String store : allProducts.keySet()) {
                // Try to find the product in this store
                Optional<Product> match = allProducts.get(store).stream()
                        .filter(p -> p.getProductId().equals(productId))
                        .findFirst();

                if (match.isEmpty()) continue;

                Product product = match.get();

                // Check for an active discount on the product in this store
                Discount discount = allDiscounts.getOrDefault(store, List.of()).stream()
                        .filter(d -> d.getProductId().equals(productId))
                        .filter(d -> {
                            LocalDate from = LocalDate.parse(d.getFromDate());
                            LocalDate to = LocalDate.parse(d.getToDate());
                            return !today.isBefore(from) && !today.isAfter(to);
                        })
                        .findFirst()
                        .orElse(null);

                // Calculate final price with discount
                int discountPercent = discount != null ? discount.getPercentage() : 0;
                double finalPrice = product.getPrice() * (1 - discountPercent / 100.0);
                double subtotal = finalPrice * quantity;

                // Save this store if it's the best price so far
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
                // Log if no store had the requested product
                System.out.println("⚠️  Product with ID '" + productId + "' not found in any store for date ");
            }
        }

        // Return total cost and store-by-store breakdown
        return new BasketSplitResponseDTO(total, results);
    }
}
