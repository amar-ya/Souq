package com.example.Souq.order;

import com.example.Souq.cart.CartEntity;
import com.example.Souq.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

    private LocalDateTime createdAt;
    private String status;
    private double totalPrice;


}
