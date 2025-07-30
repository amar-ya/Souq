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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_seq")
    @SequenceGenerator(name = "prod_seq", sequenceName = "prod_seq", allocationSize = 1)
    private Integer id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private UserEntity seller;
}
