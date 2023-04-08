package com.example.controller.customer;

import com.example.domain.entity.Customer;
import com.example.domain.entity.CustomerBalanceHistory;
import com.example.dto.ChangeBalanceDto;
import com.example.dto.CustomerDto;
import com.example.exception.customer.CustomerNotExistException;
import com.example.service.customer.CustomerBalanceHistoryService;
import com.example.service.customer.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.common.UserVo;
import org.example.domain.config.JwtAuthenticationProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/customer")
@RestController
public class CustomerController {

    private static final String HEADER = "Authorization";

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final CustomerService customerService;

    private final CustomerBalanceHistoryService customerBalanceHistoryService;

    @ApiOperation("회원정보 조회")
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

    @ApiOperation("예치금 금액 변경")
    @PostMapping("/balance")
    public ResponseEntity<Integer> changeBalance(
            @RequestHeader(name = HEADER) String token,
            @RequestBody ChangeBalanceDto request) {

        UserVo userVo = this.jwtAuthenticationProvider.getUserVo(token);

        return ResponseEntity.ok(
                this.customerBalanceHistoryService.changeBalance(userVo.getUserId(),request).getCurrentBalance()
        );
    }
}
