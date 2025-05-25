package com.roxana.pricecomparator.service;

import com.roxana.pricecomparator.dto.SubstituteProductDTO;
import com.roxana.pricecomparator.model.Product;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class RecommendationService {
    private final ProductService productService;

    public RecommendationService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Suggests substitute products that belong to the same category and use the same unit
     * of measurement (e.g., kg, l), and returns them sorted by value per unit (ascending).
     *
     * This helps users find better deals, even if the product is from a different brand or store.
     *
     * @param productId The ID of the product for which to find substitutes
     * @return A list of alternative products sorted by price per unit (best first)
     */
    public List<SubstituteProductDTO> getSubstitutes(String productId) {
        // Load all products from all stores
        Map<String, List<Product>> allProducts = productService.getAllProducts();

        // Find the reference product based on the requested productId
        Product reference = allProducts.values().stream()
                .flatMap(List::stream)
                .filter(p -> p.getProductId().equalsIgnoreCase(productId))
                .findFirst()
                .orElse(null);

        // If product not found, return empty list
        if (reference == null) return List.of();

        // Filter logic: we want products from the same category and with same unit type
        String category = reference.getProductCategory();
        String unit = reference.getPackageUnit();

        return allProducts.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .filter(p -> p.getProductCategory().equalsIgnoreCase(category))
                        .filter(p -> p.getPackageUnit().equalsIgnoreCase(unit))
                        .map(p -> new SubstituteProductDTO(
                                p.getProductId(),
                                p.getProductName(),
                                p.getBrand(),
                                entry.getKey(), // store name
                                p.getPrice(),
                                p.getPackageQuantity(),
                                p.getPackageUnit(),
                                p.getPrice() / p.getPackageQuantity() // price per unit
                        ))
                )
                .sorted(Comparator.comparingDouble(SubstituteProductDTO::getPricePerUnit)) // best value first
                .toList();
    }
}
