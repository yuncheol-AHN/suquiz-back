package com.example.entity.socialLogin.oauth.oauthApi.response;

import com.example.entity.domain.OAuthProvider;
import com.example.entity.socialLogin.oauth.oauthApi.client.OAuthApiClient;
import com.example.entity.socialLogin.oauth.oauthApi.client.RevokeTokenResponseDto;
import com.example.entity.socialLogin.oauth.oauthApi.params.OAuthLoginParams;
import com.example.entity.socialLogin.oauth.oauthApi.params.OAuthLogoutParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RequestOAuthInfoService {
    private final Map<OAuthProvider, OAuthApiClient> clients;

    public RequestOAuthInfoService(List<OAuthApiClient> clients) {
        this.clients = clients.stream().collect(
                Collectors.toUnmodifiableMap(OAuthApiClient::oAuthProvider, Function.identity())
        );
    }

    public OAuthInfoResponse request(OAuthLoginParams params) {
        OAuthApiClient client = clients.get(params.oAuthProvider());
        String accessToken = client.requestAccessToken(params);
        OAuthInfoResponse res = client.requestOauthInfo(accessToken);
        log.info("Object : " + res.getEmail());
        log.info("name : "+ res.getNickname());
        log.info("kakao : " + res.getOAuthProvider());
        // 로그인 유저 정보에 대한 값들을 리턴
        return client.requestOauthInfo(accessToken);
    }

    public RevokeTokenResponseDto logoutService(OAuthLogoutParams params) {
        OAuthApiClient client = clients.get(params.oAuthProvider()); // 클라이언트의 제공자 확인

        return client.revokeAccessToken(params);
    }

}
