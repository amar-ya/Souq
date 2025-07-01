package com.example.Souq.order;


import com.example.Souq.cart.CartEntity;
import com.example.Souq.cart.CartItem.CartItemEntity;
import com.example.Souq.cart.CartItem.CartItemRepository;
import com.example.Souq.cart.CartRepository;
import com.example.Souq.order.orderItem.OrderItemEntity;
import com.example.Souq.order.orderItem.OrderItemRepository;
import com.example.Souq.user.UserEntity;
import io.jsonwebtoken.RequiredTypeException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public OrderEntity placeOrder(UserEntity user)
    {
        //1. Get the User`s cart
        CartEntity cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RequiredTypeException("cart not found"));

        List<CartItemEntity> cartItems = cartItemRepository.findByCart(cart);

        if(cartItems.isEmpty())
        {
            throw new RequiredTypeException("cart not found");
        }


        //2. Create the order
        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PLACED");

        double total = 0;
        List<OrderItemEntity> orderItems = new ArrayList<>();

        for (CartItemEntity cartItem : cartItems)
        {
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtPurchase(cartItem.getProduct().getPrice());

            double itemTotal = cartItem.getProduct().getPrice() * cartItem.getQuantity();
            total += itemTotal;

            orderItems.add(orderItem);
        }

        //3. save order and order items
        order.setTotalPrice(total);
        order = orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        //4. Clean up the cart
        cartItemRepository.deleteAll(cartItems);

        return order;
    }
}
