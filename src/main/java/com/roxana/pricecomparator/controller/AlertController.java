package com.roxana.pricecomparator.controller;

import com.roxana.pricecomparator.dto.PriceAlertDTO;
import com.roxana.pricecomparator.model.Product;
import com.roxana.pricecomparator.service.AlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "*")
public class AlertController {
    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<String> addAlert(@RequestBody PriceAlertDTO alert) {
        alertService.addAlert(alert);
        return ResponseEntity.ok("Alert added for product " + alert.getProductId());
    }

    @GetMapping("/triggered")
    public ResponseEntity<List<Product>> getTriggeredAlerts() {
        return ResponseEntity.ok(alertService.checkAlerts());
    }

    @GetMapping
    public ResponseEntity<List<PriceAlertDTO>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }
}
