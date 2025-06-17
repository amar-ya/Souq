package com.example.Souq.CartItem;

import com.example.Souq.cart.CartEntity;
import com.example.Souq.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer>
{
    @Query("select c from CartItemEntity c where c.cart = :cart and c.product = :product")
    Optional<CartItemEntity> findByCartAndProduct(CartEntity cart, ProductEntity product);

    @Query("select c from CartItemEntity c where c.createdAt < :expirationThreshold")
    List<CartItemEntity> findByCreatedAtBefore(LocalDateTime expirationThreshold);
}
