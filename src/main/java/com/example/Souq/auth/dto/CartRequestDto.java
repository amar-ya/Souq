package com.example.Souq.auth.dto;

import lombok.Data;

@Data
public class CartRequestDto
{
    private Integer productId;
    private Integer quantity;
}
