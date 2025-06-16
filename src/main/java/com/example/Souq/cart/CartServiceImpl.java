package com.example.Souq.cart;


import com.example.Souq.auth.dto.CartRequestDto;
import com.example.Souq.product.ProductEntity;
import com.example.Souq.product.ProductRepository;
import com.example.Souq.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService
{
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public CartEntity addItemToCart(CartRequestDto dto, UserEntity buyer)
    {
        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartEntity cartItem = new CartEntity();
        cartItem.setProduct(product);
        cartItem.setBuyer(buyer);
        cartItem.setQuantity(dto.getQuantity());
        cartItem.setCreatedAt(LocalDateTime.now());

        return cartRepository.save(cartItem);
    }
}
