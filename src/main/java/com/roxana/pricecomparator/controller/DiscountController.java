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

    @GetMapping("/new")
    public ResponseEntity<List<Discount>> getNewDiscounts() {
        return ResponseEntity.ok(discountService.getNewDiscounts());
    }

    @GetMapping("/best")
    public ResponseEntity<List<Discount>> getTopDiscounts(@RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(discountService.getTopDiscounts(limit));
    }
}
