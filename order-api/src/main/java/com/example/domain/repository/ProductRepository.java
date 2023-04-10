package com.example.domain.repository;

import com.example.domain.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @EntityGraph(attributePaths = "productItemList")
    Optional<Product> findBySellerIdAndId(Long sellerId, Long id);
}
