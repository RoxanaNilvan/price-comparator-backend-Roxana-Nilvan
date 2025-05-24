package com.roxana.pricecomparator.dto;

public class BasketProductDetailDTO {
    private String productId;
    private String name;
    private double price;
    private int discount;
    private double finalPrice;
    private int quantity;
    private double subtotal;

    public BasketProductDetailDTO() {

    }

    public BasketProductDetailDTO(String productId, String name, double price, int discount, double finalPrice, int quantity, double subtotal) {
        this.productId = productId;
        this.name = name;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
