package org.example.domain.config;

import org.example.domain.common.UserType;
import org.example.domain.util.Aes256Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.domain.common.UserVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

import static io.jsonwebtoken.lang.Strings.hasText;

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
        return this.parseClaims(token).getExpiration().before(new Date());
    }

    public UserVo getUserVo(String token) {
        Claims claims = this.parseClaims(token);
        return new UserVo(Long.valueOf(Aes256Util.decrypt(claims.getId())), Aes256Util.decrypt(claims.getSubject()));
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJwt(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}