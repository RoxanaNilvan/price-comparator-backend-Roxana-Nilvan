package com.roxana.pricecomparator.dto;

public class SubstituteProductDTO {
    private String productId;
    private String name;
    private String brand;
    private String store;
    private double price;
    private double quantity;
    private String unit;
    private double pricePerUnit;

    public SubstituteProductDTO(String productId, String name, String brand, String store,
                                double price, double quantity, String unit, double pricePerUnit) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.store = store;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
    }

    // Gettere
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public String getStore() { return store; }
    public double getPrice() { return price; }
    public double getQuantity() { return quantity; }
    public String getUnit() { return unit; }
    public double getPricePerUnit() { return pricePerUnit; }
}
