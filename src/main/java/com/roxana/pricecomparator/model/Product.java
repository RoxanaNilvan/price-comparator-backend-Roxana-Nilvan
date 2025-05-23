package com.roxana.pricecomparator.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Product {

    @CsvBindByName(column = "product_id")
    private String productId;

    @CsvBindByName(column = "product_name")
    private String name;

    @CsvBindByName(column = "product_category")
    private String category;

    @CsvBindByName(column = "brand")
    private String brand;

    @CsvBindByName(column = "package_quantity")
    private double quantity;

    @CsvBindByName(column = "package_unit")
    private String unit;

    @CsvBindByName(column = "price")
    private double price;

    @CsvBindByName(column = "currency")
    private String currency;
}
