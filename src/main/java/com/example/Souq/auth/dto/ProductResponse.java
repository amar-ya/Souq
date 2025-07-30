package com.example.Souq.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private double price;

    public ProductResponse(Integer id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}