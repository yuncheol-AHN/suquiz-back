package com.example.entity.socialLogin.oauth.controller;

import com.example.entity.global.dto.CommonResponse;
import com.example.entity.socialLogin.oauth.oauthApi.client.RevokeTokenResponseDto;
import com.example.entity.socialLogin.oauth.oauthApi.params.KakaoLoginParams;
import com.example.entity.socialLogin.oauth.oauthApi.params.NaverLoginParams;
import com.example.entity.socialLogin.oauth.oauthApi.params.NaverLogoutParams;
import com.example.entity.socialLogin.oauth.oauthApi.response.OAuthInfoResponse;
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

//    @PostMapping("/login/kakao")
//    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
//        AuthTokens login = oAuthLoginService.login(params);
//        return ResponseEntity.ok(oAuthLoginService.login(params));
//    }

    @PostMapping("/login/kakao")
    public ResponseEntity<CommonResponse> loginKakao(@RequestBody KakaoLoginParams params) {
        OAuthLoginService.LoginResult login = oAuthLoginService.login(params);
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("카카오 로그인")
                .data(login)
                .build(), HttpStatus.OK);
    }

    //    @PostMapping("/login/naver")
//    public ResponseEntity<AuthTokens> loginNaver(@RequestBody NaverLoginParams params) {
//        return ResponseEntity.ok(oAuthLoginService.login(params));
//    }
    @PostMapping("/login/naver")
    public ResponseEntity<CommonResponse> loginNaver(@RequestBody NaverLoginParams params) {
        OAuthLoginService.LoginResult login = oAuthLoginService.login(params);
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("네이버 로그인")
                .data(login)
                .build(), HttpStatus.OK);
    }

    // 닉네임이 있는지 판단 여부를 위한 요청
    @GetMapping("/login/checkNickname/{email}")
    public ResponseEntity<CommonResponse> checkNickname(@PathVariable String email) {
        Boolean validNick = oAuthLoginService.findNickname(email);
        if(validNick) {
            return new ResponseEntity<>(CommonResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("닉네임이 있습니다.")
                    .data(validNick)
                    .build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(CommonResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("닉네임이 없습니다.")
                    .data(validNick)
                    .build(), HttpStatus.OK);
        }
    }

    // 닉네임에 대한 등록 처리
    @PostMapping("/login/register")
    public ResponseEntity<CommonResponse> registerNickname(@RequestBody OAuthLoginService.nicknameRequest request) {
        Boolean regist = oAuthLoginService.registerNickname(request);
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("닉네임 등록 완료")
                .data(regist)
                .build(), HttpStatus.OK);
    }

    // 닉네임 중복 검사
    @GetMapping("/login/validate/{nickname}")
    public ResponseEntity<CommonResponse> validateNickname(@PathVariable("nickname") String nickname) {
        boolean findNickname = oAuthLoginService.findAllNickname(nickname);
        if (!findNickname) {
            return new ResponseEntity<>(CommonResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("닉네임이 중복되었습니다.")
                    .data(findNickname)
                    .build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(CommonResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("닉네임 생성이 가능합니다.")
                    .data(findNickname)
                    .build(), HttpStatus.OK);
        }

    }

    @PostMapping("/logout/naver")
    public ResponseEntity<CommonResponse> logoutNaver(@RequestBody NaverLogoutParams params) {
        ResponseEntity<RevokeTokenResponseDto> logout = ResponseEntity.ok(oAuthLogoutService.logout(params));
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("네이버 로그아웃")
                .data(logout)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/logout/kakao")
    public ResponseEntity<CommonResponse> logoutKakao(@RequestHeader("Authorization") String accessToken) {
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
            return new ResponseEntity<>(CommonResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("카카오 로그아웃 성공")
                    .data("logout success")
                    .build(), HttpStatus.OK);
        } else {
            // 로그아웃이 실패한 경우
            return new ResponseEntity<>(CommonResponse.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("카카오 로그아웃 실패")
                    .data("fail logout")
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
