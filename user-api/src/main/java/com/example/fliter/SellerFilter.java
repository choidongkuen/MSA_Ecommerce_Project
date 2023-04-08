package com.example.fliter;

import com.example.service.seller.SellerService;
import lombok.RequiredArgsConstructor;
import org.example.domain.common.UserVo;
import org.example.domain.config.JwtAuthenticationProvider;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/seller/*")
@RequiredArgsConstructor
public class SellerFilter implements Filter {

    private final static String HEADER = "Authorization";

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final SellerService sellerService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request; // HttpServletRequest -> ServletRequest
        String token = req.getHeader(HEADER);

        if (!jwtAuthenticationProvider.validateToken(token)) {
            throw new ServletException("유효하지 않은 접근입니다.");
        }

        UserVo userVo = jwtAuthenticationProvider.getUserVo(token);
        this.sellerService.findByIdAndEmail(userVo.getUserId(), userVo.getUserEmail())
                          .orElseThrow(() -> new ServletException("유효하지 않은 접근입니다."));

        chain.doFilter(request, response);
    }
}
