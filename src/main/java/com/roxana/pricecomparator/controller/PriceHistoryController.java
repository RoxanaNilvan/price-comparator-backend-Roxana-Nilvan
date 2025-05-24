package com.roxana.pricecomparator.controller;

import com.roxana.pricecomparator.dto.PriceHistoryResponseDTO;
import com.roxana.pricecomparator.service.PriceHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/price-history")
@CrossOrigin(origins = "*")
public class PriceHistoryController {
    private final PriceHistoryService priceHistoryService;

    public PriceHistoryController(PriceHistoryService priceHistoryService) {
        this.priceHistoryService = priceHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<PriceHistoryResponseDTO>> getHistory(
            @RequestParam Optional<String> productId,
            @RequestParam Optional<String> store,
            @RequestParam Optional<String> category,
            @RequestParam Optional<String> brand
    ) {
        return ResponseEntity.ok(
                priceHistoryService.getPriceHistory(productId, store, category, brand)
        );
    }

}
