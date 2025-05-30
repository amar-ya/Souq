package com.example.Souq.security;


import com.example.Souq.user.CustomUserDetails;
import com.example.Souq.user.UserEntity;
import com.example.Souq.user.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
        throws ServletException, IOException
    {
        final String authHeader = request.getHeader("Authorization");
        final String token;
        final String userEmail;

        //Check if header exists and starts with bearer
        if(authHeader == null || !authHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(7);
        try
        {
            userEmail = jwtUtil.getEmailFromToken(token);
        } catch (ExpiredJwtException e)
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //Only set authentication if not already done
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            //debug why im getting 403 in postman ckecking authorization header
            System.out.println("🔎 Checking Authorization header: " + request.getHeader("Authorization"));

            UserEntity user = userRepository.findByEmail(userEmail).orElse(null);
            if (user != null) {
                System.out.println("✅ JWT Filter hit");
                System.out.println("Email from token: " + userEmail);
                System.out.println("User found: " + user.getEmail() + ", Role: " + user.getRole());
                System.out.println("Setting authority: ROLE_" + user.getRole());

                CustomUserDetails userDetails = new CustomUserDetails(user);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
