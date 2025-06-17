package com.example.Souq.cart;

import com.example.Souq.CartItem.CartItemEntity;
import com.example.Souq.auth.dto.CartRemoveRequestDto;
import com.example.Souq.auth.dto.CartRequestDto;
import com.example.Souq.user.UserEntity;
import com.example.Souq.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody CartRequestDto dto, Authentication auth) {
        String email = auth.getName();
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CartItemEntity cartItem = cartService.addItemToCart(dto, user);
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeItemFromCart(@RequestBody CartRemoveRequestDto dto, Authentication auth) {
        String email = auth.getName();
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        cartService.removeItemFromCart(user, dto);
        return ResponseEntity.ok("Item(s) removed successfully.");
    }
}
