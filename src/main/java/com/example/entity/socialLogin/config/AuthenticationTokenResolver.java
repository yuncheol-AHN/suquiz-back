package com.example.entity.socialLogin.config;

import com.example.entity.socialLogin.oauth.util.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class AuthenticationTokenResolver {

    private final JwtProperties properties;

    // 인증에 관한 정보들
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token); // 토큰을 디코딩

        String subject = claims.getSubject(); // 디코딩된 인증에 대한 유저 정보
        long userId = Long.parseLong(subject); // 유저 정보에 대한 long형 변환

        return new Authentication(userId); // 그러면 인증된 유저의 아이디를 저장
    }

    // 토큰에 저장된 만료 시간이 현재 시간과 비교해서 만료되었으면 true, 아니면 false 처리
    public boolean isTokenNotExpired(String token) {
        return getClaims(token).getExpiration()
                .before(new Date());
    }


    // 토큰에 대한 디코딩 처리
    private Claims getClaims(String token) {
        Claims claim = Jwts.parser()
                .setSigningKey(properties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
        return claim;
    }

}