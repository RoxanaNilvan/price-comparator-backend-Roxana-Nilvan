package com.roxana.pricecomparator.dto;

import java.util.List;

public class BasketSplitResponseDTO {
    private double total;
    private List<BasketSplitProductDTO> products;

    public BasketSplitResponseDTO(double total, List<BasketSplitProductDTO> products) {
        this.total = total;
        this.products = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<BasketSplitProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<BasketSplitProductDTO> products) {
        this.products = products;
    }
}
