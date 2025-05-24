package com.roxana.pricecomparator.dto;

public class PriceAlertDTO {
    private String productId;
    private double targetPrice;

    public PriceAlertDTO() {}

    public PriceAlertDTO(String productId, double targetPrice) {
        this.productId = productId;
        this.targetPrice = targetPrice;
    }

    public String getProductId() { return productId; }
    public double getTargetPrice() { return targetPrice; }
}
