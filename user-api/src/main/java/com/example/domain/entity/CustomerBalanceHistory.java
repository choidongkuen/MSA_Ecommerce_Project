package com.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class CustomerBalanceHistory extends BaseEntity{

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Customer.class,fetch = FetchType.LAZY)
    private Customer customer;

    private Integer changedBalance; // 변경된 돈

    private Integer currentBalance; // 현재 잔액

    private String fromMessage;

    private String description;
}
