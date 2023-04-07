package com.example.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChangeBalanceDto {

    private String form;
    private String message;
    private Integer money;
}
