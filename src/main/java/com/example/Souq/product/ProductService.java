package com.example.Souq.product;

import com.example.Souq.auth.dto.ProductRequestDto;
import com.example.Souq.user.UserEntity;
import com.example.Souq.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService
{
    private final ProductRepository productRepository;
    private final UserRepository userRepository;



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
}
