package com.example.Souq.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>
{

    @Query("SELECT p FROM ProductEntity p WHERE p.seller.id = :sellerId")
    List<ProductEntity> findProductBySellerId(@Param("sellerId") int sellerId);
}
