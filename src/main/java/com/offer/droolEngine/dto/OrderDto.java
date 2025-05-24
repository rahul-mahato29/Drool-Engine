package com.offer.droolEngine.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {
    private String name;
    private String cardType;
    private BigDecimal price;
    private int discount;
}
