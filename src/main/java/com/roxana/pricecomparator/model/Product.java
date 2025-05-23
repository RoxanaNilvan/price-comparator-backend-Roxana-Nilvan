package com.roxana.pricecomparator.model;

import com.opencsv.bean.CsvBindByName;


public class Product {
    @CsvBindByName(column = "product_id")
    private String productId;

    @CsvBindByName(column = "product_name")
    private String productName;

    @CsvBindByName(column = "product_category")
    private String productCategory;

    @CsvBindByName(column = "brand")
    private String brand;

    @CsvBindByName(column = "package_quantity")
    private double packageQuantity;

    @CsvBindByName(column = "package_unit")
    private String packageUnit;

    @CsvBindByName(column = "price")
    private double price;

    @CsvBindByName(column = "currency")
    private String currency;

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getProductCategory() { return productCategory; }
    public String getBrand() { return brand; }
    public double getPackageQuantity() { return packageQuantity; }
    public String getPackageUnit() { return packageUnit; }
    public double getPrice() { return price; }
    public String getCurrency() { return currency; }

    public void setProductId(String productId) { this.productId = productId; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setPackageQuantity(double packageQuantity) { this.packageQuantity = packageQuantity; }
    public void setPackageUnit(String packageUnit) { this.packageUnit = packageUnit; }
    public void setPrice(double price) { this.price = price; }
    public void setCurrency(String currency) { this.currency = currency; }
}
