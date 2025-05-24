package com.offer.droolEngine.controllers;

import com.offer.droolEngine.entities.Order;
import com.offer.droolEngine.services.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping("/discount")
    public ResponseEntity<Order> applyDiscount(@RequestBody Order order) {
        Order updatedOrder = discountService.applyRules(order);
        return ResponseEntity.ok(updatedOrder);
    }
}
