package com.roxana.pricecomparator.controller;

import com.roxana.pricecomparator.model.Product;
import com.roxana.pricecomparator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Map<String, List<Product>>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{store}")
    public ResponseEntity<List<Product>> getProductsByStore(@PathVariable String store) {
        List<Product> products = productService.getAllProducts().get(store.toLowerCase());
        if (products == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }
}
