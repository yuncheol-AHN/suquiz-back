package com.example.entity.socialLogin.oauth.service;

import com.example.entity.socialLogin.oauth.oauthApi.client.RevokeTokenResponseDto;
import com.example.entity.socialLogin.oauth.oauthApi.params.OAuthLogoutParams;
import com.example.entity.socialLogin.oauth.oauthApi.response.RequestOAuthInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLogoutService {

    private final RequestOAuthInfoService requestOAuthInfoService;

    public RevokeTokenResponseDto logout(OAuthLogoutParams params) {

        RevokeTokenResponseDto responseDto = requestOAuthInfoService.logoutService(params);

        return responseDto;
    }
}
