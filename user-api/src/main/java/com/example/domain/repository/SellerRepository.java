package com.example.domain.repository;

import com.example.domain.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {


    Optional<Seller> findByEmail(String email);
    Optional<Seller> findByIdAndEmail(Long id, String email);


}
