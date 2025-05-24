package com.roxana.pricecomparator.service;

import com.roxana.pricecomparator.dto.PriceAlertDTO;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlertCSVService {
    private final String ALERTS_FILE = "alerts.csv";

    public void saveAlerts(List<PriceAlertDTO> alerts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ALERTS_FILE))) {
            writer.println("product_id,target_price");
            for (PriceAlertDTO alert : alerts) {
                writer.println(alert.getProductId() + "," + alert.getTargetPrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<PriceAlertDTO> loadAlerts() {
        List<PriceAlertDTO> alerts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ALERTS_FILE))) {
            String line;
            reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String productId = parts[0];
                    double targetPrice = Double.parseDouble(parts[1]);
                    alerts.add(new PriceAlertDTO(productId, targetPrice));
                }
            }
        } catch (IOException e) {
            // No file yet — OK
        }
        return alerts;
    }
}
