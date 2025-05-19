package com.example.Souq.auth.dto;

import com.example.Souq.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse
{
    private String token;
    private String username;
    private Role role;



}
