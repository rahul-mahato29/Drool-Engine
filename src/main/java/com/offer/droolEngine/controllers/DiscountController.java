package com.offer.droolEngine.controllers;

import com.offer.droolEngine.dto.OrderDto;
import com.offer.droolEngine.entities.Order;
import com.offer.droolEngine.services.DiscountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;
    private final ModelMapper modelMapper;

    @PostMapping("/discount")
    public ResponseEntity<OrderDto> applyDiscount(@RequestBody OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        Order updatedOrder = discountService.applyRules(order);
        return ResponseEntity.ok(modelMapper.map(updatedOrder, OrderDto.class));
    }
}
