package com.ch10.jwt;

import com.ch10.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Component
public class JwtProvider {

    private String issuer;
    private SecretKey secretKey;

    public JwtProvider(@Value("${jwt.issuer}") String issuer,
                       @Value("${jwt.secret}") String secretKey) {
        this.issuer = issuer;
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // JWT 토큰 생성
    public String createToken(User user, int days) {

        // 발급일, 만료일 생성
        Date issueDate = new Date();
        Date expireDate = new Date(issueDate.getTime() + Duration.ofDays(days).toMillis());

        // 클레임 생성 - 토큰의 정보
        Claims claims = Jwts.claims();
        claims.put("username", user.getUid());
        claims.put("role", user.getRole());

        // 토큰 생성
        String token = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(issuer)
                .setIssuedAt(issueDate)
                .setExpiration(expireDate)
                .addClaims(claims)
                .signWith(this.secretKey, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    // 토큰으로부터 클레임 추출
    public Claims getClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(this.secretKey)
                .build()
                .parseClaimsJws(token).getBody();
    }

    // 인증 사용자 객체
    public UsernamePasswordAuthenticationToken getAuthentication(String token) {

        // 토큰으로부터 사용자 정보 추출
        Claims claims = getClaims(token);
        String username = (String) claims.get("username");
        String role = (String) claims.get("role");

        // User 엔티티 생성
        User user = User
                .builder()
                .uid(username)
                .role(role)
                .build();

        // 사용자 권한 목록
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole())); // 계정권한 앞에 접두어 ROLE_ 붙여야 됨


        return new UsernamePasswordAuthenticationToken(user, token, authorities);
    }

    public void validateToken(String token) throws Exception {

        try {
            // 토큰 검사(유효성, 만료일)
            Jwts.parserBuilder()
                    .setSigningKey(this.secretKey)
                    .build()
                    .parseClaimsJws(token);
            
        }catch (Exception e) {
            // 토큰에 문제가 있을 경우 예외 넘기기
            throw new Exception(e);
        }
    }


}
