package com.example.Souq.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class UserEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Integer id;
    private String username;
    private String email;
    private String pn;
    private String password;
    private LocalDateTime createdAt;
    private boolean isVerified;

    @Enumerated(EnumType.STRING)
    private Role role;

}


