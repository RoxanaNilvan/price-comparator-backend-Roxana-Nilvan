package com.roxana.pricecomparator.service;

import com.roxana.pricecomparator.dto.PriceAlertDTO;
import com.roxana.pricecomparator.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AlertService {
    private final ProductService productService;
    private final List<PriceAlertDTO> alerts = new ArrayList<>();

    public AlertService(ProductService productService) {
        this.productService = productService;
    }

    public void addAlert(PriceAlertDTO alert) {
        alerts.add(alert);
    }

    public List<Product> checkAlerts() {
        Map<String, List<Product>> allProducts = productService.getAllProducts();

        return alerts.stream()
                .flatMap(alert -> allProducts.entrySet().stream()
                        .flatMap(e -> e.getValue().stream()
                                .filter(p -> p.getProductId().equals(alert.getProductId()))
                                .filter(p -> p.getPrice() <= alert.getTargetPrice())
                        )
                )
                .toList();
    }
}
