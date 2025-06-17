package com.example.Souq.auth.dto;

import lombok.Data;

@Data
public class CartRemoveRequestDto
{
    private Integer productId;
    private int quantity;
}
