package com.example.Souq.auth.dto;

import com.example.Souq.user.Role;
import lombok.Data;

@Data
public class RegisterRequest
{
    private String username;
    private String email;
    private String pn;
    private String password;
    private Role role;
}
