package com.example.Souq.cart;

import com.example.Souq.auth.dto.CartRequestDto;
import com.example.Souq.user.UserEntity;
import org.springframework.stereotype.Service;


public interface CartService
{
    CartEntity addItemToCart(CartRequestDto dto, UserEntity buyer);
}
