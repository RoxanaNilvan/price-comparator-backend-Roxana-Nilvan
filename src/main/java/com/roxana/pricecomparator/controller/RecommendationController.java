package com.roxana.pricecomparator.controller;

import com.roxana.pricecomparator.dto.SubstituteProductDTO;
import com.roxana.pricecomparator.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = "*")
public class RecommendationController {
    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<SubstituteProductDTO>> getSubstitutes(@PathVariable String productId) {
        List<SubstituteProductDTO> substitutes = recommendationService.getSubstitutes(productId);
        if (substitutes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(substitutes);
    }
}
