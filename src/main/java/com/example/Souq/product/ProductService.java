package com.example.Souq.product;

import com.example.Souq.auth.dto.*;
import com.example.Souq.order.OrderEntity;
import com.example.Souq.order.OrderRepository;
import com.example.Souq.user.UserEntity;
import com.example.Souq.user.UserRepository;
import com.example.Souq.auth.dto.ProductResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService
{
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;



    public ProductEntity createProduct(ProductRequestDto dto, UserEntity seller) {
        ProductEntity product = new ProductEntity();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setCreatedAt(LocalDateTime.now());
        product.setSeller(seller);

        return productRepository.save(product);
    }


    public List<ProductEntity> getProductsBySeller(int sellerId)
    {
        return productRepository.findProductBySellerId(sellerId);
    }

    public List<ProductResponse> getAllPublicProducts()
    {
        List<ProductEntity> product = productRepository.findAll();

        return product.stream()
                .map(products -> new ProductResponse(
                        products.getId(),
                        products.getName(),
                        products.getDescription(),
                        products.getPrice()
                ))
                .toList();
    }


}
