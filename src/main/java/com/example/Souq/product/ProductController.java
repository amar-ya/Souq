package com.example.Souq.product;

import com.example.Souq.auth.dto.ProductRequestDto;
import com.example.Souq.auth.dto.ProductResponse;
import com.example.Souq.user.UserEntity;
import com.example.Souq.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequestDto dto, Authentication auth )
    {

        System.out.println("🔐 User: " + auth.getName());
        System.out.println("🔐 Authorities: " + auth.getAuthorities());

        String email = auth.getName();
        UserEntity seller = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        ProductEntity saved = productService.createProduct(dto, seller);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/browse")
    public ResponseEntity<?> getAllProduct()
    {
        List<ProductEntity> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/public/browse")
    public ResponseEntity<List<ProductResponse>> getAllPublicProducts()
    {
        List<ProductResponse> products = productService.getAllPublicProducts();
        return ResponseEntity.ok(products);
    }
}
