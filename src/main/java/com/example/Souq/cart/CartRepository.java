package com.example.Souq.cart;

import com.example.Souq.product.ProductEntity;
import com.example.Souq.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer>
{
    @Query("select c from CartEntity c where c.user = :user")
    Optional<CartEntity> findByUser(UserEntity user);

}
