package com.roxana.pricecomparator.controller;

import com.roxana.pricecomparator.dto.BasketRequestItemDTO;
import com.roxana.pricecomparator.dto.BasketResponseDTO;
import com.roxana.pricecomparator.dto.BasketSplitResponseDTO;
import com.roxana.pricecomparator.service.BasketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basket")
@CrossOrigin(origins = "*")
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/optimize/split")
    public ResponseEntity<BasketSplitResponseDTO> optimizeSplit(@RequestBody List<BasketRequestItemDTO> items) {
        BasketSplitResponseDTO result = basketService.optimizeSplit(items);
        return ResponseEntity.ok(result);
    }
}
