package com.example.Souq.cart;

import com.example.Souq.cart.CartItem.CartItemEntity;
import com.example.Souq.cart.CartItem.CartItemRepository;
import com.example.Souq.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartCleanupService
{

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    //run every hour
    @Scheduled(fixedRate = 360000)
    public void cleanupExpiredCartItems()
    {
        LocalDateTime expirationThreshold = LocalDateTime.now().minusHours(1);
        List<CartItemEntity> expiredItems = cartItemRepository.findByCreatedAtBefore(expirationThreshold);

        for(CartItemEntity item : expiredItems)
        {
            var product = item.getProduct();
            product.setQuantity(product.getQuantity() + item.getQuantity());
            productRepository.save(product);

            cartItemRepository.delete(item);
        }

        System.out.println("Expired cart items cleaned up.");
    }
}
