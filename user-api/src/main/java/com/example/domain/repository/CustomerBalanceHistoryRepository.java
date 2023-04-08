package com.example.domain.repository;

import com.example.domain.entity.CustomerBalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
public interface CustomerBalanceHistoryRepository extends JpaRepository<CustomerBalanceHistory,Long> {

    Optional<CustomerBalanceHistory> findFirstByCustomer_IdOrderByIdDesc(@RequestParam(name = "customer_id") Long id); // 가장 최신의 기록
}
