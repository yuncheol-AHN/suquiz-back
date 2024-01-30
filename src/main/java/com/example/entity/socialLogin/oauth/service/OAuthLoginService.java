package com.example.entity.socialLogin.oauth.service;

import com.example.entity.user.domain.User;
import com.example.entity.user.repository.UserRepository;
import com.example.entity.socialLogin.oauth.oauthApi.params.OAuthLoginParams;
import com.example.entity.socialLogin.oauth.oauthApi.response.OAuthInfoResponse;
import com.example.entity.socialLogin.oauth.tokens.AuthTokens;
import com.example.entity.socialLogin.oauth.oauthApi.response.RequestOAuthInfoService;
import com.example.entity.socialLogin.oauth.tokens.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        // param 값을 통한, access, refresh, grantType, 유효시간을 반환한다.
        Long memberId = findOrCreateMember(oAuthInfoResponse);

        return authTokensGenerator.generate(memberId);
        // JWT 토큰으로 엑세스 토큰, 리프래쉬 토큰이 만들어져서 리턴된다
    }


    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return userRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(User::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        User user = User.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return userRepository.save(user).getId();
    }

    private User findUser(OAuthInfoResponse oAuthInfoResponse) {
        Optional<User> findUser = userRepository.findByEmail(oAuthInfoResponse.getEmail());

        return findUser.orElse(null);
    }
}
