package com.roxana.pricecomparator.controller;

import com.roxana.pricecomparator.model.Discount;
import com.roxana.pricecomparator.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/discounts")
@CrossOrigin(origins = "*")
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public ResponseEntity<Map<String, List<Discount>>> getAllDiscounts() {
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

    @GetMapping("/{store}")
    public ResponseEntity<List<Discount>> getDiscountsForStore(@PathVariable String store) {
        List<Discount> discounts = discountService.getDiscountsForStore(store.toLowerCase());
        if (discounts == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(discounts);
    }
}
