package com.roxana.pricecomparator.model;

import com.opencsv.bean.CsvBindByName;



public class Discount {
    @CsvBindByName(column = "product_id")
    private String productId;

    @CsvBindByName(column = "product_name")
    private String productName;

    @CsvBindByName(column = "brand")
    private String brand;

    @CsvBindByName(column = "package_quantity")
    private double packageQuantity;

    @CsvBindByName(column = "package_unit")
    private String packageUnit;

    @CsvBindByName(column = "product_category")
    private String productCategory;

    @CsvBindByName(column = "from_date")
    private String fromDate;

    @CsvBindByName(column = "to_date")
    private String toDate;

    @CsvBindByName(column = "percentage_of_discount")
    private int percentage;

    // === GETTERE ===
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getBrand() { return brand; }
    public double getPackageQuantity() { return packageQuantity; }
    public String getPackageUnit() { return packageUnit; }
    public String getProductCategory() { return productCategory; }
    public String getFromDate() { return fromDate; }
    public String getToDate() { return toDate; }
    public int getPercentage() { return percentage; }

    // === SETTERE ===
    public void setProductId(String productId) { this.productId = productId; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setPackageQuantity(double packageQuantity) { this.packageQuantity = packageQuantity; }
    public void setPackageUnit(String packageUnit) { this.packageUnit = packageUnit; }
    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }
    public void setFromDate(String fromDate) { this.fromDate = fromDate; }
    public void setToDate(String toDate) { this.toDate = toDate; }
    public void setPercentage(int percentage) { this.percentage = percentage; }
}
