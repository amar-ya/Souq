package com.example.Souq.order;


import com.example.Souq.product.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_items")
@Data
public class OrderItemEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "order_item_seq")
    @SequenceGenerator(name = "order_item_seq", sequenceName = "order_item_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    private int quantity;
    private double priceAtPurchase;


}
