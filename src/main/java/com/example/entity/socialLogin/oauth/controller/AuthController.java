package com.example.entity.socialLogin.oauth.controller;

import com.example.entity.socialLogin.oauth.oauthApi.client.RevokeTokenResponseDto;
import com.example.entity.socialLogin.oauth.oauthApi.params.KakaoLoginParams;
import com.example.entity.socialLogin.oauth.oauthApi.params.NaverLoginParams;
import com.example.entity.socialLogin.oauth.oauthApi.params.NaverLogoutParams;
import com.example.entity.socialLogin.oauth.service.OAuthLoginService;
import com.example.entity.socialLogin.oauth.service.OAuthLogoutService;
import com.example.entity.socialLogin.oauth.tokens.AuthTokens;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AuthController {
    private final OAuthLoginService oAuthLoginService;
    private final OAuthLogoutService oAuthLogoutService;
    private final RestTemplate restTemplate;

    @Value("${oauth.kakao.url.logout}")
    private String kakaoLogoutUrl;
    @Value("${oauth.kakao.client-id}")
    private String kakaoClientId;
    @Value("${oauth.kakao.url.redirect}")
    private String kakaoLogoutRedirectUrl;

    @PostMapping("/login/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    @PostMapping("/login/naver")
    public ResponseEntity<AuthTokens> loginNaver(@RequestBody NaverLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    @PostMapping("/logout/naver")
    public ResponseEntity<RevokeTokenResponseDto> logoutNaver(@RequestBody NaverLogoutParams params) {
        return ResponseEntity.ok(oAuthLogoutService.logout(params));
    }

    @GetMapping("/logout/kakao")
    public ResponseEntity<String> logoutKakao(@RequestHeader("Authorization") String accessToken) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", kakaoClientId);
        params.add("logout_redirect_uri", kakaoLogoutRedirectUrl);
//        params.add("state", "your_unique_state_value"); // CSRF 방지를 위한 임의의 문자열

        // 로그아웃 URL 생성
        String url = "https://kauth.kakao.com/oauth/logout" + "?" +
                params.entrySet().stream()
                        .map(entry -> entry.getKey() + "=" + entry.getValue().get(0))
                        .reduce((p1, p2) -> p1 + "&" + p2)
                        .orElse("");

        // 로그아웃 요청
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.FOUND) {
            // 로그아웃이 성공적으로 수행되었을 경우
            return new ResponseEntity<>("logout success", HttpStatus.OK);
        } else {
            // 로그아웃이 실패한 경우
            return new ResponseEntity<>("fail logout", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
