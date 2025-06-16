package com.example.Souq.cart;

import com.example.Souq.product.ProductEntity;
import com.example.Souq.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "cart_items")
@Data
public class CartEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    @SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private UserEntity buyer;

    private int quantity;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
