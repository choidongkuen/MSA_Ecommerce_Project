package com.example.controller.customer;

import com.example.domain.entity.Customer;
import com.example.dto.CustomerDto;
import com.example.exception.customer.CustomerNotExistException;
import com.example.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.common.UserVo;
import org.example.domain.config.JwtAuthenticationProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/customer")
@RestController
public class CustomerController {

    private static final String HEADER = "Authorization";

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final CustomerService customerService;

    @GetMapping("/userInfo")
    public ResponseEntity<CustomerDto> getInfo(
            @RequestHeader(name = HEADER) String token
    ) {
        UserVo userVo = this.jwtAuthenticationProvider.getUserVo(token);

        Customer customer =
                this.customerService.findByIdAndEmail(userVo.getUserId(), userVo.getUserEmail())
                                    .orElseThrow(() -> new CustomerNotExistException("일치하는 회원 정보가 존재하지 않습니다."));

        return ResponseEntity.ok().body(CustomerDto.fromEntity(customer));
    }
}
