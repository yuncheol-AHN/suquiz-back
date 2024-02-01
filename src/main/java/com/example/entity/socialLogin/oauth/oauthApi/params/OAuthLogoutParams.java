package com.example.entity.socialLogin.oauth.oauthApi.params;

import com.example.entity.domain.OAuthProvider;
import org.springframework.util.MultiValueMap;

public interface OAuthLogoutParams {

    OAuthProvider oAuthProvider();
    MultiValueMap<String, String> makebody();

}
