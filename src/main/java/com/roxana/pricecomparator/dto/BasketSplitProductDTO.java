package com.roxana.pricecomparator.dto;

public class BasketSplitProductDTO {
    private String productId;
    private String store;
    private String name;
    private double basePrice;
    private int discount;
    private double finalPrice;
    private int quantity;
    private double subtotal;


    public BasketSplitProductDTO(String productId, String store, String name, double basePrice, int discount,
                                 double finalPrice, int quantity, double subtotal) {
        this.productId = productId;
        this.store = store;
        this.name = name;
        this.basePrice = basePrice;
        this.discount = discount;
        this.finalPrice = finalPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
