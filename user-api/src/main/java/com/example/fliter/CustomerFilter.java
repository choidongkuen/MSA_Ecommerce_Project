package com.example.fliter;

import com.example.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.example.domain.common.UserVo;
import org.example.domain.config.JwtAuthenticationProvider;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/customer/*") // /customer/** 로 들어오는 요청에 대한 필터
@RequiredArgsConstructor
public class CustomerFilter implements Filter {

    private final static String HEADER = "Authorization";

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final CustomerService customerService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request; // HttpServletRequest -> ServletRequest
        String token = req.getHeader(HEADER);

        if(!jwtAuthenticationProvider.validateToken(token)) {
            throw new ServletException("유효하지 않은 접근입니다.");
        }

        UserVo userVo = jwtAuthenticationProvider.getUserVo(token);
        this.customerService.findByIdAndEmail(userVo.getUserId(), userVo.getUserEmail())
                .orElseThrow(() -> new ServletException("유효하지 않은 접근입니다.")); // 해당 회원 존재 여부 판단

        chain.doFilter(request,response);
    }
}
