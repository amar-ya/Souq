package com.example.Souq.auth.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto
{
    private List<Integer> cartItemIds;
}
