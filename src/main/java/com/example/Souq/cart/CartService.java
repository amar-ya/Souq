package com.example.Souq.cart;


import com.example.Souq.cart.CartItem.CartItemEntity;
import com.example.Souq.cart.CartItem.CartItemRepository;
import com.example.Souq.auth.dto.CartRemoveRequestDto;
import com.example.Souq.auth.dto.CartRequestDto;
import com.example.Souq.product.ProductEntity;
import com.example.Souq.product.ProductRepository;
import com.example.Souq.user.UserEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService
{
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartEntity getOrCreateCart(UserEntity user) {
        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    CartEntity newCart = new CartEntity();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
    }

    public CartItemEntity addItemToCart(CartRequestDto dto, UserEntity buyer) {
        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < dto.getQuantity()) {
            throw new RuntimeException("Not enough product in stock");
        }

        CartEntity cart = getOrCreateCart(buyer);

        Optional<CartItemEntity> existingItemOpt = cartItemRepository.findByCartAndProduct(cart, product);

        CartItemEntity item;
        if (existingItemOpt.isPresent()) {
            item = existingItemOpt.get();
            item.setQuantity(item.getQuantity() + dto.getQuantity());
        } else {
            item = new CartItemEntity();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(dto.getQuantity());
            item.setCreatedAt(LocalDateTime.now());
        }

        product.setQuantity(product.getQuantity() - dto.getQuantity());
        productRepository.save(product);

        return cartItemRepository.save(item);
    }





    @Transactional
    public void removeItemFromCart(UserEntity user, CartRemoveRequestDto dto) {
        CartEntity cart = getOrCreateCart(user);
        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItemEntity item = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseThrow(() -> new RuntimeException("Item not found in cart"));

        int currentQuantity = item.getQuantity();
        int removeQuantity = dto.getQuantity();

        if (removeQuantity >= currentQuantity) {
            // Delete entire cart item
            cartItemRepository.delete(item);
            product.setQuantity(product.getQuantity() + currentQuantity);
        } else {
            // Reduce quantity only
            item.setQuantity(currentQuantity - removeQuantity);
            cartItemRepository.save(item);
            product.setQuantity(product.getQuantity() + removeQuantity);
        }

        productRepository.save(product);
    }


}
