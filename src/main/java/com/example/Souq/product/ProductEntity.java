package com.example.Souq.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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
}
