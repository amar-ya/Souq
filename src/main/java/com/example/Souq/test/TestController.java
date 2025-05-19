package com.example.Souq.test;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Token is valid ✅");
    }

    @PreAuthorize("hasRole('BUYER')")
    @GetMapping("/test/buyer")
    public ResponseEntity<String> testBuyer() {
        return ResponseEntity.ok("Hello Buyer!");
    }
    @GetMapping("/seller")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<String> onlySellerAccess()
    {
        return ResponseEntity.ok("✅ Seller access confirmed.");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> onlyAdminAccess() {
        return ResponseEntity.ok("✅ Admin access confirmed.");
    }

    //@PreAuthorize("hasRole('BUYER')")
    @GetMapping("/test/buyerr")
    public ResponseEntity<?> testBuyer(Authentication authentication) {
        System.out.println("✅ User: " + authentication.getName());
        System.out.println("✅ Authorities: " + authentication.getAuthorities());
        return ResponseEntity.ok("Accessible!");

    }
}