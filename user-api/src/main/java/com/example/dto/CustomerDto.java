package com.example.dto;


import com.example.domain.entity.Customer;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long id;

    private String email;

    public static CustomerDto fromEntity(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getEmail());
    }
}
