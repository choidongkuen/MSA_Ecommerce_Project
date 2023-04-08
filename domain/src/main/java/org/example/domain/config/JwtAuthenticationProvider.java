package org.example.domain.config;

import io.jsonwebtoken.*;
import org.example.domain.common.UserType;
import org.example.domain.util.Aes256Util;

import org.example.domain.common.UserVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Objects;

import static org.springframework.util.StringUtils.hasText;


@Configuration
public class JwtAuthenticationProvider {

    @Value("${jwt.key}")
    private String secretKey;

    private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 60; // 1시간

    public String createToken(String userPk, Long id, UserType userType) {
        Claims claims = Jwts.claims()
                            .setSubject(Aes256Util.encrypt(userPk))
                            .setId(Aes256Util.encrypt(id.toString()));

        claims.put("roles", userType);
        Date now = new Date();

        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuedAt(now)
                   .setExpiration(new Date(now.getTime() + TOKEN_EXPIRE_TIME))
                   .signWith(SignatureAlgorithm.HS256, secretKey)
                   .compact();
    }

    public boolean validateToken(String token) {
        if (!hasText(token)) {
            return false;
        }

        try{
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    // 만료기간이 지나면 false
    // 만료기간 안지나면 true

    public UserVo getUserVo(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
        return new UserVo(Long.valueOf(Objects.requireNonNull(Aes256Util.decrypt(claims.getId()))), Aes256Util.decrypt(claims.getSubject()));
    }
}
