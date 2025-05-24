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

    public List<SubstituteProductDTO> getSubstitutes(String productId) {
        Map<String, List<Product>> allProducts = productService.getAllProducts();

        Product reference = allProducts.values().stream()
                .flatMap(List::stream)
                .filter(p -> p.getProductId().equalsIgnoreCase(productId))
                .findFirst()
                .orElse(null);

        if (reference == null) return List.of();

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
                                p.getPrice() / p.getPackageQuantity()
                        ))
                )
                .sorted(Comparator.comparingDouble(SubstituteProductDTO::getPricePerUnit))
                .toList();
    }
}
