package com.offer.droolEngine.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {
    private String name;
    private String cardType;
    private BigDecimal price;
    private int discount;
}
