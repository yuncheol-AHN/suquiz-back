package com.example.entity.socialLogin.oauth.oauthApi.response;


import com.example.entity.user.domain.OAuthProvider;

public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    OAuthProvider getOAuthProvider();
//    String getAccessToken();
//    String getRefreshToken();
}
