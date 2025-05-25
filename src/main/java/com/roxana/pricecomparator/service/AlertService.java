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
    private final AlertCSVService alertCSVService;
    private List<PriceAlertDTO> alerts = new ArrayList<>();

    public AlertService(ProductService productService, AlertCSVService alertCSVService) {
        this.productService = productService;
        this.alertCSVService = alertCSVService;
        this.alerts = alertCSVService.loadAlerts();
    }

    public void addAlert(PriceAlertDTO alert) {
        alerts.add(alert);
        alertCSVService.saveAlerts(alerts);
    }

    /**
     * Checks all stored price alerts against the current product prices
     * across all stores, and returns the list of products that match an alert
     * (i.e., their price is at or below the target price set by the user).
     *
     * @return List of products that triggered at least one alert
     */
    public List<Product> checkAlerts() {
        // Load all products currently available in all stores
        Map<String, List<Product>> allProducts = productService.getAllProducts();

        // For each alert, search all products in all stores for a match
        return alerts.stream()
                .flatMap(alert ->
                        allProducts.entrySet().stream() // iterate through each store
                                .flatMap(entry -> entry.getValue().stream() // iterate through products in that store
                                        .filter(p -> p.getProductId().equals(alert.getProductId())) // match product ID
                                        .filter(p -> p.getPrice() <= alert.getTargetPrice())         // check if price is low enough
                                )
                )
                .toList(); // collect matched products
    }

    public List<PriceAlertDTO> getAllAlerts() {
        return alerts;
    }
}
