package com.example.Souq.order;

import com.example.Souq.user.CustomUserDetails;
import com.example.Souq.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(Authentication auth)
    {
        try{
            // Get the user from the JWToken
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            UserEntity user = userDetails.getUser();


            //Place the Order
            OrderEntity order = orderService.placeOrder(user);

            //return success response
            return ResponseEntity.ok(order);
        } catch (RuntimeException e){
            //return error message
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/{orderId}/pay")
    public ResponseEntity<?> confirmPayment(
            @PathVariable Integer orderId,
            Authentication auth
    )
    {
        try{
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            UserEntity user = userDetails.getUser();

            orderService.confirmPayment(orderId, user);
            return ResponseEntity.ok("✅ Payment confirmed");
        }catch (RuntimeException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
