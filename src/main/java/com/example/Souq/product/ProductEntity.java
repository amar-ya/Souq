package com.example.Souq.product;

import com.example.Souq.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
public class ProductEntity
{
    @Id
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private UserEntity seller;
}
