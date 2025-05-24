package com.roxana.pricecomparator.dto;

import java.util.List;

public class BasketResponseDTO {
    private String store;
    private double total;
    private List<BasketProductDetailDTO> products;

    public BasketResponseDTO(String store, double total, List<BasketProductDetailDTO> products) {
        this.store = store;
        this.total = total;
        this.products = products;
    }

    public String getStore() {
        return store;
    }

    public double getTotal() {
        return total;
    }

    public List<BasketProductDetailDTO> getProducts() {
        return products;
    }
}
