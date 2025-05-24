package com.roxana.pricecomparator.dto;

import java.util.List;

public class PriceHistoryResponseDTO {
    private String productId;
    private String name;
    private String store;
    private String category;
    private String brand;
    private List<PricePointDTO> history;

    public PriceHistoryResponseDTO(String productId, String name, String store, String category, String brand, List<PricePointDTO> history) {
        this.productId = productId;
        this.name = name;
        this.store = store;
        this.category = category;
        this.brand = brand;
        this.history = history;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getStore() {
        return store;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public List<PricePointDTO> getHistory() {
        return history;
    }
}
