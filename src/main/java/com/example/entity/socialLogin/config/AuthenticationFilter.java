package com.example.entity.socialLogin.config;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    // filter를 거치지 않을 특정 url
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().startsWith("/users/login");
    }

    private static final String TOKEN_HEADER = "Authorization";
    private final AuthenticationTokenResolver tokenResolver;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            log.info("여기까지 됨0");
            String token = extractTokenFromHeader(request);
            if (tokenResolver.isTokenNotExpired(token)) {
                throw new JwtException("Invalid token exception");
            }
            Authentication authentication = tokenResolver.getAuthentication(token);
            log.info("test={}", authentication.toString());
            AuthenticationContextHolder.setAuthentication(authentication);
            doFilter(request, response, filterChain);

        } catch (Exception e) {
            log.debug(e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        } finally {
            AuthenticationContextHolder.clearContext();
        }

    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        String authorization = request.getHeader(TOKEN_HEADER);
        if (authorization == null) {
            throw new IllegalArgumentException("Token not found");
        }
        try {
            return authorization.split(" ")[1];
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid token format");
        }
    }

}
